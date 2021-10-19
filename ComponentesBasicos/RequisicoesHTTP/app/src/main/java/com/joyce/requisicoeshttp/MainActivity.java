package com.joyce.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText cepTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        cepTxt = findViewById(R.id.txt_cep);

        button.setOnClickListener(v -> {
            
            /*//sem retrofit
            MyAsyncTask task = new MyAsyncTask();
            String urlApi = "https://blockchain.info/ticker";
            String cep = cepTxt.getText().toString();
            String urlApiCEP = "https://viacep.com.br/ws/"+ cep + "/json/";
            task.execute(urlApiCEP);*/
        });
    }

    /*//sem retrofit
    class MyAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String urlApi = strings[0];  //utilizando essa string conseguimos criar uma conexao http
            InputStream  inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = new StringBuffer(); //ao utilizar StringBuffer é possivel montar linha a linha o reader. No final termos uma string grande com a resposta da requesicao

            try {

                URL url = new URL(urlApi); //cria uma conexao http e converte a urlApi em uma URL mesmo
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // url.openConnection cria requisicao e retorna um objeto do tipo HttpUrlConnection(nos permite recuperar os dados da requisicao)

                //recupera dados da requisicao em bytes
                inputStream = connection.getInputStream(); //esses dados vem encapsulados e nao conseguimos exibli-los como texto, ELES VEM EM BYTES

                //inputStreamReader le os dados em bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader(inputStream);

                //Agora é preciso converter esses caracteres para um formato que consigamos exibir
                BufferedReader reader = new BufferedReader(inputStreamReader); // objeto utilizado para a leitura dos caracteres do inputStreamReader
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) { // s é a string de resultado que a funcao doInBackground retornou
            super.onPostExecute(s);

            String logradouro = null;
            String bairro = null;
            String localidade = null;

            //trata o resultado antes de fazer a exibiçao, criando um JSONObject
            try {
                JSONObject jsonObject = new JSONObject(s);
                logradouro = jsonObject.getString("logradouro");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");

                s = "Logradouro: " + logradouro + "\nBairro: " + bairro + "/" + localidade;


            } catch (JSONException e) {
                e.printStackTrace();
            }

            textView.setText(s);
        }
    }*/
}