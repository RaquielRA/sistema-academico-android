package com.example.sistemaacademicoandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;
import com.example.sistemaacademicoandroid.model.Usuario;
import com.example.sistemaacademicoandroid.sistema.Sistema;
import com.example.sistemaacademicoandroid.ui.administrador.AdministradorActivity;
import com.example.sistemaacademicoandroid.ui.aluno.AlunoActivity;
import com.example.sistemaacademicoandroid.ui.professor.ProfessorActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private Button btnLogin;
    private TextView tvMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
        tvMensagem = findViewById(R.id.tvMensagem);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String senha = etSenha.getText().toString().trim();

            // Autenticação via Sistema
            Usuario usuario = Sistema.getInstancia().autenticarUsuario(email, senha);

            if (usuario != null) {
                tvMensagem.setText("Autenticação bem-sucedida!");

                Intent intent;
                switch (usuario.getTipoUsuario()) {
                    case ADMIN:
                        intent = new Intent(this, AdministradorActivity.class);
                        break;
                    case PROFESSOR:
                        intent = new Intent(this, ProfessorActivity.class);
                        break;
                    case ALUNO:
                        intent = new Intent(this, AlunoActivity.class);
                        break;
                    default:
                        intent = null;
                }

                if (intent != null) {
                    // Passa o ID do usuário logado para a próxima Activity
                    intent.putExtra("usuarioId", usuario.getId());
                    startActivity(intent);
                    finish(); // fecha tela de login
                } else {
                    tvMensagem.setText("Erro inesperado: tipo de usuário inválido.");
                }

            } else {
                tvMensagem.setText("Login ou senha incorretos.");
            }
        });
    }
}
