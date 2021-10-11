package com.joyce.organizze.activitys;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.joyce.organizze.R;
import com.joyce.organizze.config.ConfiguracaoFirebase;
import com.joyce.organizze.adapter.AdapterMovimentacoes;
import com.joyce.organizze.helper.Base64Custom;
import com.joyce.organizze.model.Movimentacoes;
import com.joyce.organizze.model.Usuario;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private TextView saudacao, saldo;
    private RecyclerView recycleMovimentacoes;

    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAuth();
    private DatabaseReference db = ConfiguracaoFirebase.getFirebaseDb();
    private DatabaseReference usuarioRef;
    private ValueEventListener valueEventListenerUsuario; //objeto que pode tratar e receber um valueEventListever
    private ValueEventListener valueEventListenerMovimentacao; //objeto que pode tratar e receber um valueEventListever

    private List<Movimentacoes> movimentacoes = new ArrayList<>();
    private Movimentacoes movimentacao;
    private AdapterMovimentacoes adapterMovimentacoes;
    private DatabaseReference movimentacaoRef;

    private String mesAnoSelecionado;

    private double saldoTotal = 0.0;
    private double receitaTotal = 0.0;
    private double despesaTotal = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        calendarView = findViewById(R.id.calendarView);
        saldo = findViewById(R.id.txtsSaldo);
        saudacao = findViewById(R.id.txtSaudacao);
        recycleMovimentacoes = findViewById(R.id.recyclerMovimentacoes);

        configuraCalendarView();
        swipe();

        //configura adapter para recyclerView
       adapterMovimentacoes = new AdapterMovimentacoes(movimentacoes, this);


        //configura RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycleMovimentacoes.setLayoutManager(layoutManager);
        recycleMovimentacoes.setHasFixedSize(true);
        recycleMovimentacoes.setAdapter(adapterMovimentacoes);

        /*calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

            }
        });*/

    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarResumo();
        recuperarMovimentacoes();
    }

    private void configuraCalendarView() {

        CharSequence[] meses = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro","Outubro", "Novembro", "Dezembro"};
        calendarView.setTitleMonths(meses);

        CalendarDay dataAtual = calendarView.getCurrentDate();
        String mesSelect = String.format("%02d", dataAtual.getMonth() + 1);
        mesAnoSelecionado =  String.valueOf(mesSelect + "" + dataAtual.getYear());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mesSelect = String.format("%02d", date.getMonth() + 1);
                mesAnoSelecionado =  String.valueOf(mesSelect+ "" + date.getYear());
                movimentacaoRef.removeEventListener(valueEventListenerMovimentacao);
                recuperarMovimentacoes();
            }
        });
    }

    public void adicionarDespesa(View v){
        startActivity(new Intent(this, DespesaActivity.class));
    }

    public void adicionarReceita(View v){
        startActivity(new Intent(this, ReceitaActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu); //getMenuInflater converte o objeto xml em uma View

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:
                auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void recuperarResumo(){

        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        usuarioRef = db.child("usuarios").child(idUsuario);

        valueEventListenerUsuario = usuarioRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue(Usuario.class);

                receitaTotal = usuario.getReceitaTotal();
                despesaTotal = usuario.getDespesaTotal();
                saldoTotal =receitaTotal - despesaTotal;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");

                saudacao.setText("Olá, " + usuario.getNome() + "!");
                saldo.setText("R$ " + decimalFormat.format(saldoTotal));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        usuarioRef.removeEventListener(valueEventListenerUsuario);
        movimentacaoRef.removeEventListener(valueEventListenerMovimentacao);
    }

    public void atualizarSaldo(){

        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        usuarioRef = db.child("usuarios").child(idUsuario);

        if(movimentacao.getTipo().equals("r")){
            receitaTotal = receitaTotal - movimentacao.getValor();
            usuarioRef.child("receitaTotal").setValue(receitaTotal);
        }else {
            despesaTotal = despesaTotal - movimentacao.getValor();
            usuarioRef.child("despesaTotal").setValue(despesaTotal);
        }

    }

    public void recuperarMovimentacoes(){

        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
        movimentacaoRef = db.child("movimentacoes")
                            .child(idUsuario)
                            .child(mesAnoSelecionado);

        valueEventListenerMovimentacao = movimentacaoRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //metodo que consegue recuperar os dados da movimentacao
                movimentacoes.clear();

                for(DataSnapshot dados: snapshot.getChildren()){
                    Movimentacoes movimentacao = dados.getValue(Movimentacoes.class);
                    movimentacao.setId(dados.getKey());
                    movimentacoes.add(movimentacao);
                }

                adapterMovimentacoes.notifyDataSetChanged(); //notificar que os dados foram atualizados
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void swipe(){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                excluirMovimentacao(viewHolder);

            }
        };

        new ItemTouchHelper(itemTouch).attachToRecyclerView(recycleMovimentacoes);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void excluirMovimentacao(RecyclerView.ViewHolder viewHolder){

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Excluir Movimentaçao da Conta");
        dialog.setMessage("Você tem certeza que deseja excluir movimentaçao?");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Confirmar", (dialog1, which) -> {

            int position = viewHolder.getAdapterPosition();
            movimentacao = movimentacoes.get(position);

            String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());
            movimentacaoRef = db.child("movimentacoes")
                    .child(idUsuario)
                    .child(mesAnoSelecionado);

            movimentacaoRef.child(movimentacao.getId()).removeValue();
            adapterMovimentacoes.notifyItemRemoved(position);
            atualizarSaldo();
        });
        dialog.setNegativeButton("Cancelar", (dialog12, which) -> {
            Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
            adapterMovimentacoes.notifyDataSetChanged();
        });

        AlertDialog alert = dialog.create();
        alert.show();

    }
}