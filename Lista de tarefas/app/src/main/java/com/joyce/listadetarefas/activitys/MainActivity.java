package com.joyce.listadetarefas.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.joyce.listadetarefas.R;
import com.joyce.listadetarefas.adapter.TarefaAdapter;
import com.joyce.listadetarefas.helper.DbHelper;
import com.joyce.listadetarefas.helper.RecyclerItemClickListener;
import com.joyce.listadetarefas.helper.TarefaDAO;
import com.joyce.listadetarefas.model.Tarefa;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerListaTarefas;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private Tarefa tarefaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerListaTarefas = findViewById(R.id.reciclerListaTarefas);

        //adicionando evento de click no recyclerView
        recyclerListaTarefas.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(), recyclerListaTarefas, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Click normal apenas edita a tarefa
                // 1 -> Recupera tarefa para edicao
                tarefaSelecionada = listaTarefas.get(position);

                // 2 -> Envia a tarefa para tela adcionar tarefa
                Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                intent.putExtra("tarefaSelecionada", tarefaSelecionada);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                //Click longo faz a deleçao da tarefa
                tarefaSelecionada = listaTarefas.get(position);
                //tarefaDAO = new TarefaDAO(getApplicationContext());

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                //Configura título e msg do alert:
                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getDescricao() + "?");

                //Configura os botoes do alert:
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                        if(tarefaDAO.deletar(tarefaSelecionada)){
                            listarTarefas();
                            Toast.makeText(getApplicationContext(), "Tarefa excluida com sucesso!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Não foi possível deletar tarefa.", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                dialog.setNegativeButton("Não", null);
                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdicionarTarefaActivity.class));
            }
        });
    }

    public void listarTarefas(){

        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        listaTarefas = tarefaDAO.listar();

         //   Exibe lista de tarefas no RecyclerView

        //Configurar um adapter
        tarefaAdapter = new TarefaAdapter(listaTarefas);

        //Configurar recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerListaTarefas.setLayoutManager(layoutManager);
        recyclerListaTarefas.setHasFixedSize(true);
        recyclerListaTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerListaTarefas.setAdapter(tarefaAdapter);
    }

    @Override
    protected void onStart() {
        listarTarefas();
        super.onStart();

    }
}