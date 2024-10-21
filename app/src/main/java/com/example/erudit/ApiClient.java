package com.example.erudit;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.erudit.Modals.GameRecord;
import com.example.erudit.Modals.Player;
import com.example.erudit.Modals.Question;

import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

//    private static final String BASE_URL = "http://10.0.2.2:8081/";
    private static final String BASE_URL = "http://26.151.113.226:8081/";
    private final ApiService apiService;

    public ApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public void getQuestion(Context context, ApiCallback<Question> callback) {
        Call<Question> call = apiService.getQuestion();
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Toast.makeText(context, "Response not successful", Toast.LENGTH_SHORT).show();
                    callback.onFailure(new Throwable("Response not successful"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Question> call, @NonNull Throwable t) {
                Toast.makeText(context, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                callback.onFailure(t);
            }
        });
    }

    public void getRaiting(Context context, ApiCallback callback) {
        Call<List<Player>> call = apiService.getRaiting();

        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful()) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {

            }
        });
    }

    public void postGameRecord(GameRecord gameRecord, ApiCallback callback) {
        Call<Integer> call = apiService.postGameRecord(gameRecord);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void postUsername(Context context, String username, ApiCallback callback) {
        Call<Player> call = apiService.postUsername(username);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    try {
                        callback.onSuccess(response.body());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    callback.onFailure(new Throwable("Ошибка при создании"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Player> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result) throws URISyntaxException;
        void onFailure(Throwable t);
    }
}