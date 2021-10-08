package com.joyce.organizze.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.joyce.organizze.R;
import com.joyce.organizze.config.ConfiguracaoFirebase;
import com.joyce.organizze.helper.Base64Custom;
import com.joyce.organizze.helper.DateUtil;
import com.joyce.organizze.model.Movimentacoes;
import com.joyce.organizze.model.Usuario;

public class ReceitaActivity extends AppCompatActivity {

    private EditText editData, editCategoria, editDesc, editValor;

    private Movimentacoes movimentacao;

    private DatabaseReference db = ConfiguracaoFirebase.getFirebaseDb();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAuth();

    private Double receitaTotal;
    private Double receitaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        editData = findViewById(R.id.editDateReceita);
        editCategoria = findViewById(R.id.editCategoriaReceita);
        editDesc = findViewById(R.id.editDescReceita);
        editValor = findViewById(R.id.editValorReceita);

        //preenche o campo data com a data atual
        editData.setText(DateUtil.dataAtual());

        recuperarReceitaTotal();

    }



    public void salvarReceita(View v){

        if(validarCamposReceita()) {
            String data = editData.getText().toString();
            Double valor = Double.parseDouble(editValor.getText().toString());
            movimentacao = new Movimentacoes();
            movimentacao.setData(data);
            movimentacao.setCategoria(editCategoria.getText().toString());
            movimentacao.setTipo("r");
            movimentacao.setDescricao(editDesc.getText().toString());
            movimentacao.setValor(valor);

            receitaAtualizada = receitaTotal + valor;
            atualizaReceita(receitaAtualizada);

            movimentacao.salvarMovimentacao(data);
        }
    }

    public boolean validarCamposReceita(){

        String data = editData.getText().toString();
        String categoria = editCategoria.getText().toString();
        String descricao = editDesc.getText().toString();
        String valor = editValor.getText().toString();

        if(!data.isEmpty()){
            if (!categoria.isEmpty()){
                if(!descricao.isEmpty()){
                    if(!valor.isEmpty()){
                        return true;
                    }else {
                        Toast.makeText(this, "Preencha o campo valor!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }else {
                    Toast.makeText(this, "Preencha o campo descrição!", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }else {
                Toast.makeText(this, "Preencha o campo categoria!", Toast.LENGTH_SHORT).show();
                return false;
            }

        }else {
            Toast.makeText(this, "Preencha o campo data!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void recuperarReceitaTotal(){

        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());

        DatabaseReference usuarioRef = db.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                receitaTotal= usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void atualizaReceita(Double receita){

        String idUsuario = Base64Custom.codificarBase64(autenticacao.getCurrentUser().getEmail());
        DatabaseReference usuarioRef = db.child("usuarios").child(idUsuario);
        usuarioRef.child("receitaTotal").setValue(receita);

    }

}