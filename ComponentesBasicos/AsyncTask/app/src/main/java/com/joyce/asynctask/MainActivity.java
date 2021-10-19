package com.joyce.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
    }

    public void iniciarAsyncTask(View view) {
        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);
    }

    /* Os 3 parametros do generics da classe abaixo sao:
    * 1 -> Parametro a ser passado para a classe
    * 2 -> Tipo de valor que será utilizado para o progresso da tarefa
    * 3 -> Retorno apos tarefa finalizada */
    class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //é executado no escopo de uma thread paralela e nao na thread principal

            int numero = integers[0];
            for(int i=0; i<numero; i++){
                publishProgress(i);
                try {
                    Thread.sleep(1000); //a thread paralela aguarda 1 segundo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}