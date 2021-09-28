package com.joyce.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button save;
    private TextInputEditText input;
    private TextView salvos;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.btn_salvar);
        input = findViewById(R.id.input);
        salvos = findViewById(R.id.txt_salvos);

        save.setOnClickListener(v -> {

            //classe que salva nas preferencias do usuario. Ela cria um arquivo xml, que nele conseguimos salvar as informacoes
            SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0); //primeiro parametro é o nome do arquivo xml que vamos criar, e o segundo é o modo, qe no caso é privado 0 (só nosso app pode salvar e ler esse arquivo)
            SharedPreferences.Editor editor = preferences.edit(); //retorna um objeto que é capaz de editar o arquivo xml

            //validando nome
            if(input.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Nome não pode ser vazio!", Toast.LENGTH_SHORT).show();
            }else {
                //salva os dados
                String nome = input.getText().toString();
                editor.putString("nome", nome);
                editor.commit(); //salva os dados
                salvos.setText("Olá, " + nome);
            }
        });

        //recupera os dados salvos
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //valida se temos o nome em preferences
        if(preferences.contains("nome")){

            String nome = preferences.getString("nome", "Nao contem valor");
            salvos.setText("Olá, " + nome);

        }else {
            salvos.setText("Olá, usuário não definido");
        }


    }
}