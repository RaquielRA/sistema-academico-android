package com.example.sistemaacademicoandroid.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;
import com.example.sistemaacademicoandroid.model.Professor;
import com.example.sistemaacademicoandroid.model.Aluno;
import com.example.sistemaacademicoandroid.model.Turma;
import com.example.sistemaacademicoandroid.model.Disciplina;
import com.example.sistemaacademicoandroid.sistema.Sistema;

import java.util.List;

public class ProfessorActivity extends AppCompatActivity {

    private Professor professor; // professor logado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        TextView tvBoasVindas = findViewById(R.id.tvBoasVindasProfessor);
        Button btnVerTurmas = findViewById(R.id.btnVerTurmasProfessor);
        Button btnVerAlunos = findViewById(R.id.btnVerAlunos);
        Button btnLancarNotas = findViewById(R.id.btnLancarNotas);
        Button btnLancarFaltas = findViewById(R.id.btnLancarFaltas);

        // Receber usuarioId do Intent
        int usuarioId = getIntent().getIntExtra("usuarioId", -1);
        if (usuarioId != -1) {
            professor = Sistema.getInstancia().buscarProfessorPorId(usuarioId);
        }

        if (professor == null) {
            Toast.makeText(this, "Erro: professor não encontrado.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Exibir nome do professor
        tvBoasVindas.setText("Bem-vindo, " + professor.getNome());

        // Botão Ver Turmas
        btnVerTurmas.setOnClickListener(v -> {
            List<String> turmas = professor.verTurmas();
            Toast.makeText(this, turmas.isEmpty() ? "Nenhuma turma atribuída." : turmas.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão Ver Alunos (primeira turma do professor)
        btnVerAlunos.setOnClickListener(v -> {
            if (professor.getTurmasIds().isEmpty()) {
                Toast.makeText(this, "Nenhuma turma atribuída.", Toast.LENGTH_SHORT).show();
                return;
            }
            int turmaId = professor.getTurmasIds().get(0);
            List<String> alunos = professor.verAlunos(turmaId);
            Turma turma = Sistema.getInstancia().buscarTurmaPorId(turmaId);
            StringBuilder sb = new StringBuilder();
            sb.append("Turma: ").append(turma != null ? turma.getNome() : "Desconhecida").append("\nAlunos: ").append(alunos);
            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        });

        // Botão Lançar Notas
        btnLancarNotas.setOnClickListener(v -> {
            Toast.makeText(this, "Funcionalidade de lançar notas ainda não implementada.", Toast.LENGTH_SHORT).show();
            // futuramente abrirá Activity ou Dialog para lançar notas
        });

        // Botão Lançar Faltas
        btnLancarFaltas.setOnClickListener(v -> {
            Toast.makeText(this, "Funcionalidade de lançar faltas ainda não implementada.", Toast.LENGTH_SHORT).show();
            // futuramente abrirá Activity ou Dialog para lançar faltas
        });
    }
}
