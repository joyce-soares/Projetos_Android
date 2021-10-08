package com.joyce.organizze.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.joyce.organizze.R;
import com.joyce.organizze.config.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.lilas)
                .fragment(R.layout.intro_1)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.lilas)
                .fragment(R.layout.intro_2)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.lilas)
                .fragment(R.layout.intro_3)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.lilas)
                .fragment(R.layout.intro_4)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.lilas)
                .fragment(R.layout.intro_cadastro)
                .build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        validarUsuarioLogado();
    }

    public void btEntrar(View v){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastreSe(View v){
        startActivity(new Intent(this, CadastroActivity.class));
    }

    public void validarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        //autenticacao.signOut();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
    }
}