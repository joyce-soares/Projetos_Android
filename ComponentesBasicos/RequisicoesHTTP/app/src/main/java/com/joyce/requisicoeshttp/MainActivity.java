package com.joyce.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.joyce.requisicoeshttp.api.CEPService;
import com.joyce.requisicoeshttp.api.DataService;
import com.joyce.requisicoeshttp.model.CEP;
import com.joyce.requisicoeshttp.model.Foto;
import com.joyce.requisicoeshttp.model.Post;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;
    private EditText cepTxt;

    private Retrofit retrofit;

    private List<Foto> fotos = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        cepTxt = findViewById(R.id.txt_cep);

        //TODO com retrofit, configuracao do objeto retrofit
        //String urlApiCEP = "https://viacep.com.br/ws/";
        //String urlApiFakeFotos = "https://jsonplaceholder.typicode.com";
        String urlApiFakePosts = "https://jsonplaceholder.typicode.com/";
        retrofit = new Retrofit.Builder()
                //.baseUrl(urlApiCEP)
                .baseUrl(urlApiFakePosts)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        button.setOnClickListener(v -> {
            
            /*//TODO sem retrofit
            MyAsyncTask task = new MyAsyncTask();
            String urlApi = "https://blockchain.info/ticker";
            String cep = cepTxt.getText().toString();
            String urlApiCEP = "https://viacep.com.br/ws/"+ cep + "/json/";
            task.execute(urlApiCEP);*/

            //TODO COM retrofit recupera cep
            //recuperarCEPRetrofit();

            //Todo recupera uma lista de fotos/posts
            //recuperarListaRetrofit();

            //TODO salva um post, requisicao POST
            salvarPost();
        });
    }

    private void recuperarListaRetrofit(){

        DataService service = retrofit.create(DataService.class);
        //Call<List<Foto>> call = service.recuperarFotos();
        Call<List<Post>> call = service.recuperarPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    //fotos = response.body();
                    posts = response.body();

                    for(int i=0; i< posts.size(); i++){
                        Post post =posts.get(i);
                        Log.d("Post: ", post.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }

    private void recuperarCEPRetrofit(){

        String cep = cepTxt.getText().toString();
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP(cep);

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {

                if(response.isSuccessful()){
                    CEP cep = response.body();
                    textView.setText("Logradouro: " + cep.getLogradouro() + "\nBairro: " + cep.getBairro() + "/" + cep.getLocalidade());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        }); //chamando o metodo enqueue automaticamente é gerada uma tarefa assincrona
    }

    /*//TODO sem retrofit
    class MyAsyncTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String urlApi = strings[0];  //utilizando essa string conseguimos criar uma conexao http
            InputStream  inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = new StringBuffer(); //ao utilizar StringBuffer é possivel montar linha a linha o reader. No final termos uma string grande com a resposta da requesicao

            try {

                URL url = new URL(urlApi); //cria uma conexao http e converte a urlApi em uma URL mesmo
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // url.openConnection cria requisicao e retorna um objeto do tipo HttpUrlConnection(nos permite recuperar os dados da requisicao)

                //recupera dados da requisicao em bytes
                inputStream = connection.getInputStream(); //esses dados vem encapsulados e nao conseguimos exibli-los como texto, ELES VEM EM BYTES

                //inputStreamReader le os dados em bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader(inputStream);

                //Agora é preciso converter esses caracteres para um formato que consigamos exibir
                BufferedReader reader = new BufferedReader(inputStreamReader); // objeto utilizado para a leitura dos caracteres do inputStreamReader
                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) { // s é a string de resultado que a funcao doInBackground retornou
            super.onPostExecute(s);

            String logradouro = null;
            String bairro = null;
            String localidade = null;

            //trata o resultado antes de fazer a exibiçao, criando um JSONObject
            try {
                JSONObject jsonObject = new JSONObject(s);
                logradouro = jsonObject.getString("logradouro");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");

                s = "Logradouro: " + logradouro + "\nBairro: " + bairro + "/" + localidade;


            } catch (JSONException e) {
                e.printStackTrace();
            }

            textView.setText(s);
        }
    }*/

    private void salvarPost(){

        Post post = new Post("10", "Meu novo Post", "rhkuijgt jht4lifn hgti nv");

        DataService service = retrofit.create(DataService.class);
        Call<Post> call = service.salvarPost(post);

        call.enqueue(new Callback<Post>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()){
                    Post post1 = response.body();
                   textView.setText(
                           "Codigo: " + response.code()
                           + " Id: " + post1.getId()
                           + " Titulo: " + post1.getTitle());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}