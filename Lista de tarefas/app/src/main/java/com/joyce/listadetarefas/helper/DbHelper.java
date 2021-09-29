package com.joyce.listadetarefas.helper;

//Classe para criacao do bando de dados

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NAME_DB = "DB_TAREFAS";
    public static final String TABELA_TAREFAS = "tarefas";


    public DbHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //utiliza esse metodo para criar a primeira vez o DB//

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL ); ";

        try{
            db.execSQL(sql);
            Log.i("INFO_DB", "Sucesso ao criar tabela");
        }catch (Exception e){
            Log.i("INFO_DB", "Erro ao criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //metodo utlizado para quando for criado uma outra versao do app para fazer atualizacoes de tabelas
    }
}
