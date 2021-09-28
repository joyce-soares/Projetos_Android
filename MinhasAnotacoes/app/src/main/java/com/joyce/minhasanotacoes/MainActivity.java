package com.joyce.minhasanotacoes;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.joyce.minhasanotacoes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AnotacaoPreferencias preferencias;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private EditText anotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anotacao = findViewById(R.id.editTxtAnotacao);

        preferencias = new AnotacaoPreferencias(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab);


        fab.setOnClickListener(view -> {

                //validar se foi digitado algo
                if(anotacao.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Anotação vazia!!", Toast.LENGTH_SHORT).show();
                }else {
                    String an = anotacao.getText().toString();
                    preferencias.salvarAnatocao(an);
                    Snackbar.make(view, "Anotação salva com sucesso!!", Snackbar.LENGTH_LONG).show();
                }
        });

        //recuperar anotaçao
        String anotacaoRecuperada = preferencias.recuperarAnotacao();
        if (!anotacaoRecuperada.equals("")) {
            anotacao.setText(anotacaoRecuperada);
        }
    }

}