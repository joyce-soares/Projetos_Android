package com.joyce.organizze.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.joyce.organizze.R;
import com.joyce.organizze.config.ConfiguracaoFirebase;
import com.joyce.organizze.model.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button btn_cadastro;

    private FirebaseAuth autenticacao;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        campoNome = findViewById(R.id.input_nome);
        campoEmail = findViewById(R.id.input_email);
        campoSenha = findViewById(R.id.input_senha);
        btn_cadastro = findViewById(R.id.btn_cadastro);

        btn_cadastro.setOnClickListener((v) -> {

            String txtNome = campoNome.getText().toString();
            String txtEmail = campoEmail.getText().toString();
            String txtSenha = campoSenha.getText().toString();

            //valida se os campos foram preenchidos:
           if(!txtNome.isEmpty()){
               if(!txtEmail.isEmpty()){
                   if(!txtSenha.isEmpty()){
                       usuario = new Usuario();
                       usuario.setNome(txtNome);
                       usuario.setEmail(txtEmail);
                       usuario.setSenha(txtSenha);
                    cadastrarUsuario();

                   }else {
                       Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                   }

               }else {
                   Toast.makeText(this, "Preencha o campo Email", Toast.LENGTH_SHORT).show();
               }

           }else {
               Toast.makeText(this, "Preencha o campo Nome", Toast.LENGTH_SHORT).show();
           }

        });


    }

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                    .addOnCompleteListener(this, (task) -> {
                        if(task.isSuccessful()){
                            Toast.makeText(this, "Sucesso ao cadastrar usuário!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                        }
                    });

    }
}