package com.example.sistemaacademicoandroid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sistemaacademicoandroid.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2500; // 2,5 segundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Posta um Runnable após o delay para abrir a LoginActivity
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // fecha a SplashActivity para que o usuário não possa voltar a ela
        }, SPLASH_DELAY);
    }
}
