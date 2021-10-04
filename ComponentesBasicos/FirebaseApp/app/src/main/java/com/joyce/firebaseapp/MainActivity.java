package com.joyce.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference(); //databasereference é ima classe para salvar e recuperar dados do banco de dados do firebase
    private FirebaseAuth user = FirebaseAuth.getInstance(); //classe para autenticacao de usuario


    private ImageView foto;
    private Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*DatabaseReference usuarios = reference.child("user");
        DatabaseReference produtos = reference.child("produtos");

        Salvar dados no firebase
        Usuario usuario = new Usuario();
        usuario.setNome("Rafael");;
        usuario.setIdade(25);;
        usuario.setSobrenom("Ramos");

        usuarios.child("001").setValue(usuario);

        Produto produto = new Produto();
        produto.setDesc("Notebook");
        produto.setMarca("Positivo");
        produto.setPreco(9.999);

        produtos.child("001").setValue(produto);




        //Recuper dados no firebase
        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("Firebase",snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        user = FirebaseAuth.getInstance(); //classe para autenticacao de usuario




        //Como cadastrar um usuário
        user.createUserWithEmailAndPassword("joyce@gmail.com", "jo123456")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("CreateUser", "usuario criado com sucesso");
                        }else {
                            Log.i("CreateUser", "usuario criado com SEM sucesso");
                        }
                    }
                }); //cria usuario





        //Como verificar se o usuario está logado
        user.getCurrentUser(); //retorna um user se ele estiver logado
        if(user.getCurrentUser() != null){
            Log.i("userLogado", "usuario logado");
        }




        //Deslogar usuário:
        user.signOut();




        //Logar usuário:
        user.signInWithEmailAndPassword("joyce@gmail.com", "jo123456")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i("signUser", "usuario logado com sucesso");
                        }else {
                            Log.i("signUser", "usuario logado SEM sucesso");
                        }
                    }
                });




        //Gerando identificador único
        Usuario usuario = new Usuario();
        usuario.setNome("Bryan");;
        usuario.setIdade(32);;
        usuario.setSobrenom("Assunçao");

        usuarios.push()   //esse metodo gera um identificador unico no firebase
        .setValue(usuario);




        //Aplicando filtros
        DatabaseReference usuarioPesquisa = usuarios.child("-MlBgZO_C7e8HBeNXRsz");

        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.i("Dados do usuario: ", snapshot.getValue().toString());   //pode ser assim tmb:

                Usuario dadosUsuario = snapshot.getValue(Usuario.class);
                Log.i("Dados do usuario: ", "Nome: " + dadosUsuario.getNome() + ", Sobrenome: " + dadosUsuario.getSobrenom() + " e Idade: " + dadosUsuario.getIdade());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        //Usando query's para fazer pesquisa no banco dse dados
        //Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Joyce");
        //Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
        //Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);

        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(40);   //maior ou igual >=

        //Query usuarioPesquisa = usuarios.orderByChild("idade").endAt(30);   //menor ou igual >=

        //Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(30).endAt(40);   //entre dois valores

        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("J").endAt("J" + "\uf8ff");   //filtrando palavras
        usuarioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("Dados do usuario: ", snapshot.getValue().toString());   //pode ser assim tmb:
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

*/


        //Storage - Fazendo Upload de img:
        foto = findViewById(R.id.img_foto);
        upload = findViewById(R.id.upload);

        upload.setOnClickListener(v -> {

            //Configuracao para uma imagem ser salva em memoria
            foto.setDrawingCacheEnabled(true);  //captura a img a partir da memoria
            foto.buildDrawingCache(); //constroi a img para a memoria

            //Recupera bitmap(--mapa de bits, representacao da imagem que pode ser convertida para png, jpg--) da imagem (image a ser carregada)
            Bitmap bitmap = foto.getDrawingCache();

            //Comprime bitmap para um formato png, jpg
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos);

            //converte o baos para pixel brutos em uma matriz de bytes
            //(dados da imagem)
            byte[] dadosImagem = baos.toByteArray();  //so assim é possivel enviar a imagem para o firebase, como um array de bytes

            //define os nós a para o firebase storage: */
            StorageReference reference = FirebaseStorage.getInstance().getReference();
            StorageReference imagens = reference.child("imagens"); //cria uma pasta com o nome 'imagens' dentro do firebase
            /*
            String nomeDoArquivo = UUID.randomUUID().toString(); //gera um nome pro arquivo que nunca vai se repetir
            StorageReference imagenRef = imagens.child(nomeDoArquivo + ".jpeg"); //cria dentro da pasta um arquivo

            //Retorna objeto que irá controlar o upload:
            UploadTask uploadTask = imagenRef.putBytes(dadosImagem);

            uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Upload da imagem falhou", Toast.LENGTH_SHORT).show();
                }
            });

            uploadTask.addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imagenRef.getDownloadUrl().addOnCompleteListener(task -> {
                        Uri url = task.getResult();

                        Toast.makeText(MainActivity.this, "Sucesso ao fazer upload da imagem:  " + url.toString(), Toast.LENGTH_SHORT).show();
                    });
                }
            });

         */


            //Deletando imagem
            StorageReference imagenRef = imagens.child("76a711f9-50fc-47d3-a9ad-03f833c891b5.jpeg"); /*
            imagenRef.delete().addOnFailureListener(MainActivity.this, (t -> {
                Toast.makeText(MainActivity.this, "Erro ao deletar imagem", Toast.LENGTH_SHORT).show();
            }))
                    .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity.this, "Imagem deletada com sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    }); */


            //Baixando imagem:
            Glide.with(MainActivity.this)
                    .load(imagenRef)        //carrega uma imagem do firebase dentro de um imgView
                     .into(foto);
        });
    }
}