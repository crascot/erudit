package com.example.erudit;

import com.example.erudit.Modals.GameRecord;
import com.example.erudit.Modals.Player;
import com.example.erudit.Modals.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("question")
    Call<Question> getQuestion();

    @GET("play")
    Call<List<Player>> getRaiting();

    @POST("gameRecord")
    Call<Integer> postGameRecord(@Body GameRecord gameRecord);

    @POST("reg")
    Call<Player> postUsername(@Body String username);
}