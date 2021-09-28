package com.joyce.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrir = findViewById(R.id.btn_abrir);

        btnAbrir.setOnClickListener(v -> {
            Snackbar.make(v, "Botao pressionado", BaseTransientBottomBar.LENGTH_INDEFINITE)
                    .setAction("Confirmar", v1 -> {
                        btnAbrir.setText("Botao foi pressionado");
                    }).setActionTextColor(getResources().getColor(R.color.white)).show();
        });
    }
}