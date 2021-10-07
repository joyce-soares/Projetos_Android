package com.joyce.organizze.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.joyce.organizze.R;
import com.joyce.organizze.config.ConfiguracaoFirebase;
import com.joyce.organizze.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button btn_login;

    private FirebaseAuth autenticacao;

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        campoEmail = findViewById(R.id.email_login);
        campoSenha = findViewById(R.id.senha_login);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

               if(validaCampos(email, senha)){
                   usuario = new Usuario();

                    usuario.setEmail(email);
                    usuario.setSenha(senha);

                    sigIn();
               }
            }
        });
    }

    private boolean validaCampos(String email, String senha) {

        if(!email.isEmpty()){
            if(!senha.isEmpty()){
                return true;

            }else {
                Toast.makeText(this, "Preencha o campo Senha", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else {
            Toast.makeText(this, "Preencha o campo Email", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void sigIn(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {

                       abrirTelaPrincipal();

                    } else {

                        String excecao ;

                        try{

                            throw task.getException();

                        }catch (FirebaseAuthInvalidUserException e){
                            excecao = "Usuário nao cadastrado!";

                        }catch (FirebaseAuthInvalidCredentialsException e){
                            excecao = "Senha incorreta!";

                        }catch (Exception e){
                            excecao = "Erro ao efetuar login!" + e.getMessage();
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), excecao, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        finish();
    }
}