package com.joyce.entendendothreads;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btIniciar;
    int i;
    boolean pararExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btIniciar = findViewById(R.id.button_iniciar);
    }

    public void iniciarThread(View view){

        pararExecucao = false;
        /*
        //executando uma acao em uma thread paralela
        MyThread thread = new MyThread();
        thread.start();*/

        //executando uma acao em uma thread paralela
        MyRunnable runnable = new MyRunnable();
        new Thread( runnable ).start();


    }

    public void pararThread(View v){
        pararExecucao = true;

    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            for ( i=0; i <= 15; i++ ){

                if(pararExecucao)
                    return;

                Log.d("Thread", "contador: " + i );

                //execuntando mudanca de interface apenas na thread principal(UiThread)
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btIniciar.setText("contador " + i);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            for (i=0; i <= 15; i++ ){

                Log.d("Thread", "contador: " + i );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
