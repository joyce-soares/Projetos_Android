package com.joyce.minhasanotacoes;

import android.content.Context;
import android.content.SharedPreferences;

public class AnotacaoPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String NOME_ARQUIVO = "preferencias";
    private final String CHAVE_NOME = "nome";

    public AnotacaoPreferencias(Context c) {
        this.context = c;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, 0);
        editor = preferences.edit();
    }

    //salva anotacao
    public void salvarAnatocao(String anotacao){
        editor.putString(CHAVE_NOME, anotacao);
        editor.commit();
    }

    //recupera as anotacaes
    public String recuperarAnotacao(){
        return preferences.getString(CHAVE_NOME, "");
    }
}
