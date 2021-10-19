package com.joyce.requisicoeshttp.api;

import com.joyce.requisicoeshttp.model.Foto;
import com.joyce.requisicoeshttp.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("/photos")
    Call<List<Foto>> recuperarFotos();

    @GET("/posts")
    Call<List<Post>> recuperarPosts();

    @POST("/posts")
    Call<Post> salvarPost(@Body Post post);
}
