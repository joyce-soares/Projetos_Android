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
                 "CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT ,nome VARCHAR, idade VARCHAR)"
            );

           // database.execSQL("DROP table pessoas");

            //Inserir dados:
            //database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ana', '55')");
           // database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Rodrigo', '66')");
           // database.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Briana', '18')");

            //Updadte de dados
            database.execSQL("UPDATE pessoas SET nome = 'Joyce' WHERE id = 1");

            //remover dados
            database.execSQL("DELETE FROM pessoas WHERE id = 2");

            //Recuperar dados:
            Cursor cursor = database.rawQuery(
                    "SELECT id, nome, idade FROM pessoas", null); //cursor armazena todods os dados para que se possa percorre-los

            //indices da tabela
            cursor.moveToFirst();
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");
            int indiceId = cursor.getColumnIndex("id");

           // cursor.moveToFirst();
            while (cursor != null){

                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                String id = cursor.getString(indiceId);

                Log.i("RESULTADO - Nome: " , nome + "  Idade: " + idade + " ID: " + id );
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}