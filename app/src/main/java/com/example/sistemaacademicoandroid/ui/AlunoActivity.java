package com.example.sistemaacademicoandroid.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;
import com.example.sistemaacademicoandroid.model.Aluno;
import com.example.sistemaacademicoandroid.model.Disciplina;
import com.example.sistemaacademicoandroid.sistema.Sistema;

import java.util.List;
import java.util.Map;

public class AlunoActivity extends AppCompatActivity {

    private Aluno aluno; // aluno logado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        TextView tvBoasVindas = findViewById(R.id.tvBoasVindas);
        Button btnVerTurmas = findViewById(R.id.btnVerTurmas);
        Button btnVerDisciplinas = findViewById(R.id.btnVerDisciplinas);
        Button btnVerProfessores = findViewById(R.id.btnVerProfessores);
        Button btnVerColegas = findViewById(R.id.btnVerColegas);
        Button btnNotasEFaltas = findViewById(R.id.btnNotasEFaltas);

        // Receber usuarioId do Intent
        int usuarioId = getIntent().getIntExtra("usuarioId", -1);
        if (usuarioId != -1) {
            aluno = Sistema.getInstancia().buscarAlunoPorId(usuarioId);
        }

        if (aluno == null) {
            Toast.makeText(this, "Erro: aluno não encontrado.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Definir nome do aluno
        tvBoasVindas.setText("Bem-vindo, " + aluno.getNome());

        // Botão "Ver Turmas"
        btnVerTurmas.setOnClickListener(v -> {
            List<String> turmas = aluno.verTurmas();
            Toast.makeText(this, turmas.isEmpty() ? "Nenhuma turma." : turmas.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão "Ver Disciplinas"
        btnVerDisciplinas.setOnClickListener(v -> {
            List<String> disciplinas = aluno.verDisciplinas();
            Toast.makeText(this, disciplinas.isEmpty() ? "Nenhuma disciplina." : disciplinas.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão "Ver Professores"
        btnVerProfessores.setOnClickListener(v -> {
            List<String> professores = aluno.verProfessores();
            Toast.makeText(this, professores.isEmpty() ? "Nenhum professor." : professores.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão "Ver Colegas"
        btnVerColegas.setOnClickListener(v -> {
            Map<String, List<String>> colegas = aluno.verColegas();
            if (colegas.isEmpty()) {
                Toast.makeText(this, "Nenhum colega encontrado.", Toast.LENGTH_LONG).show();
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (String turma : colegas.keySet()) {
                sb.append(turma).append(": ").append(colegas.get(turma)).append("\n");
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão "Notas e Faltas"
        btnNotasEFaltas.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            for (Integer turmaId : aluno.getTurmasIds()) {
                Disciplina d = Sistema.getInstancia().buscarDisciplinaPorId(
                        Sistema.getInstancia().buscarTurmaPorId(turmaId).getDisciplinaId()
                );
                sb.append("Turma: ").append(Sistema.getInstancia().buscarTurmaPorId(turmaId).getNome())
                        .append("\nDisciplina: ").append(d != null ? d.getNome() : "Desconhecida").append("\n");
                sb.append("Notas e faltas ainda não lançadas\n\n");
            }
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        });
    }
}
