package com.joyce.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.joyce.recyclerview.R;
import com.joyce.recyclerview.RecyclerItemClickListener;
import com.joyce.recyclerview.adapter.Adapter;
import com.joyce.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Filme> filmes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //Listagem de filmes:
        this.criarFilmes();

        //configurar adapter;
        Adapter adapter =new Adapter(filmes);

        //configurar RecycleView:
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);

        //evento de click:
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = filmes.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado: " + filme.getNomeFilme(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = filmes.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Click Longo em: " + filme.getNomeFilme(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    public void criarFilmes(){
        Filme filme = new Filme("Homem-Aranha", "Aventura", "2016");
        filmes.add(filme);
        filme = new Filme("Mulher-Maravilha", "Fantasia", "2017");
        filmes.add(filme);
        filme = new Filme("Liga da Justiça", "Ficção", "2017");
        filmes.add(filme);
        filme = new Filme("Capitão América", "Aventura", "2018");
        filmes.add(filme);
        filme = new Filme("It-A Coisa", "Drama/Terror", "2018");
        filmes.add(filme);
        filme = new Filme("Sobrenatural", "Terror", "2014");
        filmes.add(filme);
        filme = new Filme("Pica-pau", "Animação", "2017");
        filmes.add(filme);
        filme = new Filme("A Múmia", "Terror", "2017");
        filmes.add(filme);
        filme = new Filme("A Bela e a Fera", "Romance", "2014");
        filmes.add(filme);
        filme = new Filme("Meu Malvado favorito 3", "Comédia", "2017");
        filmes.add(filme);

    }
}