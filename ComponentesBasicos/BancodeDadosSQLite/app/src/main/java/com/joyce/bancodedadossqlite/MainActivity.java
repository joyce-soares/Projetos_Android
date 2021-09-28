package com.joyce.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quando utilizamos recursos de um BD temos que colocar dentro de um try
        try {

            //Criar banco de dados:
            SQLiteDatabase database = openOrCreateDatabase("appDB", MODE_PRIVATE, null);

            //Criar tabela:
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade VARCHAR)"
            );

            //Inserir dados:
            database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Joyce', '25')");
            database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Rafael', '26')");
            database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Bryan', '8')");

            //Recuperar dados:
            Cursor cursor = database.rawQuery(
                    "SELECT nome, idade FROM pessoas", null); //cursor armazena todods os dados para que se possa percorre-los

            //indices da tabela
            cursor.moveToFirst();
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

           // cursor.moveToFirst();
            while (cursor != null){
                Log.i("RESULTADO - Nome: " , cursor.getString(indiceNome));
                Log.i("RESULTADO - Idade: " , cursor.getString(indiceIdade));

                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}