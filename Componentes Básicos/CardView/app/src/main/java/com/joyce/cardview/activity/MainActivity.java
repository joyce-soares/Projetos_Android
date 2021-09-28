package com.joyce.cardview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.joyce.cardview.R;
import com.joyce.cardview.adapter.PostagemAdapter;
import com.joyce.cardview.model.Postagens;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostegem;
    private List<Postagens> postagens = new ArrayList<>();

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerPostegem = findViewById(R.id.recyclerPostagem);

        //define layout:
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL );
        recyclerPostegem.setLayoutManager(layoutManager);

        //define adapter:
        this.prepararPostagens();
        PostagemAdapter postagemAdapter = new PostagemAdapter(postagens);
        recyclerPostegem.setAdapter(postagemAdapter);
    }

    public void prepararPostagens(){
        Postagens p = new Postagens("Joyce Soares", "#TBT Viagem Legal!!", R.drawable.imagem1);
        postagens.add(p);

        p = new Postagens("Rafael Ramos", "Aproveite nossos descontos!", R.drawable.imagem2);
        postagens.add(p);

        p = new Postagens("Bryan Assunçao", "#Paris S2", R.drawable.imagem3);
        postagens.add(p);

        p = new Postagens("Fernanda Pereira", "Que foto linda!!!!", R.drawable.imagem4);
        postagens.add(p);

    }
}