package com.example.erudit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erudit.Modals.Player;

import java.net.URISyntaxException;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {
    private UserObject app;
    private RecyclerView recyclerView;
    private PlayerAdapter playerAdapter;
    private List<Player> playerList;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        app = (UserObject) getApplication();

        apiClient = new ApiClient();

        Button newGame = findViewById(R.id.new_game);
        Button leaveGame = findViewById(R.id.leave_game);

        newGame.setOnClickListener(v -> goToGameActivity());
        leaveGame.setOnClickListener(v -> {
            app.clearPlayer();
            goToMainActivity();
        });

        getRating();
    }

    public void getRating() {
        apiClient.getRaiting(LeaderboardActivity.this, new ApiClient.ApiCallback<List<Player>>() {
            @Override
            public void onSuccess(List<Player> result) throws URISyntaxException {
                playerList = result;
                playerAdapter = new PlayerAdapter(playerList);
                recyclerView.setAdapter(playerAdapter);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Ошибка при получении игроков на странице списков");
            }
        });
    }

    private void goToGameActivity() {
        Intent intent = new Intent(LeaderboardActivity.this, GameActivity.class);
        startActivity(intent);
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LeaderboardActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
