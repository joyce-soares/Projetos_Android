package com.joyce.organizze.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.joyce.organizze.config.ConfiguracaoFirebase;

public class Usuario {

    private String idUsuario; //é o email do usuario codificado na base 64
    private String nome;
    private String email;
    private String senha;
    private Double despesaTotal = 0.00;
    private Double receitaTotal = 0.00;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public void salvarUsuario(){

        //salva o usuario no Db do firebase
        DatabaseReference db = ConfiguracaoFirebase.getFirebaseDb();
        db.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);

    }
}
