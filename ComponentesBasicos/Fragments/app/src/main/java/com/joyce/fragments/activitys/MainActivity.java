package com.joyce.fragments.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import com.joyce.fragments.R;
import com.joyce.fragments.fragments.ContatosFragment;
import com.joyce.fragments.fragments.ConversasFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

   private Button btn_conversa, btn_contato;
   private ConversasFragment conversasFragment;
   private ContatosFragment contatosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Remover sombra da ActionBar
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        btn_conversa = findViewById(R.id.btn_conversas);
        btn_contato = findViewById(R.id.btn_contatos);

        conversasFragment = new ConversasFragment();
        contatosFragment = new ContatosFragment();

        //Configurar objeto para o fragmento
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameConteudo, conversasFragment);
        transaction.commit();

        btn_conversa.setOnClickListener(v -> {
            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
            transaction1.replace(R.id.frameConteudo, conversasFragment);
            transaction1.commit();
        });

        btn_contato.setOnClickListener(v -> {
            FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
            transaction2.replace(R.id.frameConteudo, contatosFragment);
            transaction2.commit();
        });
    }
}