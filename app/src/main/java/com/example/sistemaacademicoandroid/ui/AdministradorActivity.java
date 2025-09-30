package com.example.sistemaacademicoandroid.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;
import com.example.sistemaacademicoandroid.model.Administrador;
import com.example.sistemaacademicoandroid.model.Aluno;
import com.example.sistemaacademicoandroid.model.Professor;
import com.example.sistemaacademicoandroid.model.Disciplina;
import com.example.sistemaacademicoandroid.model.Turma;
import com.example.sistemaacademicoandroid.sistema.Sistema;

import java.util.List;
import java.util.stream.Collectors;

public class AdministradorActivity extends AppCompatActivity {

    private Administrador admin; // administrador logado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        TextView tvBoasVindas = findViewById(R.id.tvBoasVindasAdmin);
        Button btnCadastrarAluno = findViewById(R.id.btnCadastrarAluno);
        Button btnCadastrarProfessor = findViewById(R.id.btnCadastrarProfessor);
        Button btnCriarDisciplina = findViewById(R.id.btnCriarDisciplina);
        Button btnCriarTurma = findViewById(R.id.btnCriarTurma);
        Button btnAdicionarAlunoTurma = findViewById(R.id.btnAdicionarAlunoTurma);
        Button btnAtribuirProfessorTurma = findViewById(R.id.btnAtribuirProfessorTurma);

        // Receber usuarioId do Intent
        int usuarioId = getIntent().getIntExtra("usuarioId", -1);
        if (usuarioId != -1) {
            admin = Sistema.getInstancia().buscarAdministradorPorId(usuarioId);
        }

        if (admin == null) {
            Toast.makeText(this, "Erro: administrador não encontrado.", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        tvBoasVindas.setText("Bem-vindo, " + admin.getNome());

        // Botão Cadastrar Aluno (exemplo)
        btnCadastrarAluno.setOnClickListener(v -> {
            List<Aluno> alunos = Sistema.getInstancia().getAlunos(); // pega todos os alunos
            String nomes;
            if (alunos.isEmpty()) {
                nomes = "Nenhum";
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < alunos.size(); i++) {
                    sb.append(alunos.get(i).getNome());
                    if (i < alunos.size() - 1) sb.append(", ");
                }
                nomes = sb.toString();
            }
            Toast.makeText(this, "Alunos cadastrados: " + nomes, Toast.LENGTH_LONG).show();
        });

        // Botão Cadastrar Professor (exemplo)
        btnCadastrarProfessor.setOnClickListener(v -> {
            List<Professor> professores = Sistema.getInstancia().getProfessores();
            String nomes;
            if (professores.isEmpty()) {
                nomes = "Nenhum";
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < professores.size(); i++) {
                    sb.append(professores.get(i).getNome());
                    if (i < professores.size() - 1) sb.append(", ");
                }
                nomes = sb.toString();
            }
            Toast.makeText(this, "Professores cadastrados: " + nomes, Toast.LENGTH_LONG).show();
        });

        // Botão Criar Disciplina (exemplo)
        btnCriarDisciplina.setOnClickListener(v -> {
            List<Disciplina> disciplinas = Sistema.getInstancia().getDisciplinas();
            String nomes;
            if (disciplinas.isEmpty()) {
                nomes = "Nenhuma";
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < disciplinas.size(); i++) {
                    sb.append(disciplinas.get(i).getNome());
                    if (i < disciplinas.size() - 1) sb.append(", ");
                }
                nomes = sb.toString();
            }
            Toast.makeText(this, "Disciplinas: " + nomes, Toast.LENGTH_LONG).show();
        });

        // Botão Criar Turma (exemplo)
        btnCriarTurma.setOnClickListener(v -> {
            List<Turma> turmas = Sistema.getInstancia().getTurmas();
            String nomes;
            if (turmas.isEmpty()) {
                nomes = "Nenhuma";
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < turmas.size(); i++) {
                    sb.append(turmas.get(i).getNome());
                    if (i < turmas.size() - 1) sb.append(", ");
                }
                nomes = sb.toString();
            }
            Toast.makeText(this, "Turmas: " + nomes, Toast.LENGTH_LONG).show();
        });

    }
}
