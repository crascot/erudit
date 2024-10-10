package com.example.erudit;

import com.example.erudit.Modals.Question;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("question")
    Call<Question> getQuestion();

    @POST("reg")
    Call<String> postUsername(@Body String username);
}