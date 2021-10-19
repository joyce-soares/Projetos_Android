package com.joyce.requisicoeshttp.api;

import com.joyce.requisicoeshttp.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);  //CALL é o processo de fazer uma requisicao e em seguida capturar uma resposta
}
