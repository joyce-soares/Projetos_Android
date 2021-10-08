package com.joyce.organizze.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.joyce.organizze.config.ConfiguracaoFirebase;
import com.joyce.organizze.helper.Base64Custom;
import com.joyce.organizze.helper.DateUtil;

public class Movimentacoes {

    private String categoria;
    private String descricao;
    private String tipo;
    private String data;
    private Double valor;

    public Movimentacoes() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void salvarMovimentacao(String data){

        FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAuth();
        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        DatabaseReference db = ConfiguracaoFirebase.getFirebaseDb();
        db.child("movimentacoes")
                .child(idUsuario)
                .child(DateUtil.mesAnoDataEscolhida(data))
                .push()
                .setValue(this);
    }
}
