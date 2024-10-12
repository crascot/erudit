package com.example.erudit;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.erudit.Modals.Player;
import com.example.erudit.Modals.Question;

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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    callback.onSuccess(response.body());
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

    public void postUsername(Context context, String username, ApiCallback callback) {
        Call<Player> call = apiService.postUsername(username);

        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(@NonNull Call<Player> call, @NonNull Response<Player> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
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

    public void joinGame(Context context, Player player, ApiCallback callback) {
        Call<Boolean> call = apiService.joinGame(player);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure(new Throwable("Ошибка подключения к игре"));
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T result);
        void onFailure(Throwable t);
    }
}