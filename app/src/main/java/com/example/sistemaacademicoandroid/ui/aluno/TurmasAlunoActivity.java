package com.example.sistemaacademicoandroid.ui.aluno;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;
import com.example.sistemaacademicoandroid.model.Aluno;
import com.example.sistemaacademicoandroid.sistema.Sistema;

import java.util.List;

public class TurmasAlunoActivity extends AppCompatActivity {

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turmas_aluno);

        TextView tvTitulo = findViewById(R.id.tvTituloTurmas);
        ListView listViewTurmas = findViewById(R.id.listViewTurmas);

        int alunoId = getIntent().getIntExtra("alunoId", -1);
        aluno = Sistema.getInstancia().buscarAlunoPorId(alunoId);

        if (aluno != null) {
            tvTitulo.setText("Turmas de " + aluno.getNome());
            List<String> turmas = aluno.verTurmas();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, turmas);
            listViewTurmas.setAdapter(adapter);
        } else {
            tvTitulo.setText("Erro ao carregar aluno.");
        }
    }
}
