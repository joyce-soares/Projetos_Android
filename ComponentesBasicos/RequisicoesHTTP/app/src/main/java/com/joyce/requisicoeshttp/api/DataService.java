package com.joyce.requisicoeshttp.api;

import com.joyce.requisicoeshttp.model.Foto;
import com.joyce.requisicoeshttp.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Post>> recuperarPosts();

    @POST("/posts")
    Call<Post> salvarPost(@Body Post post);

    @PUT("/posts/{id}")
    Call<Post> atualizarPost(@Path("id") int id, @Body Post post);

    @PATCH("/posts/{id}")
    Call<Post> atualizarPostPATCH(@Path("id") int id, @Body Post post); // a diferenca entre o put e o patch é que o pach apenas atualiza os campos que voce mandar e o put atualiza todos

    @DELETE("/posts/{id}")
    Call<Void> deletarPost(@Path("id") int id);

}
