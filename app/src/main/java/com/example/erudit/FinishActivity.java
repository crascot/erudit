package com.example.erudit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent = getIntent();
        int correctCout = intent.getIntExtra("correctCout", 0);

        TextView count = findViewById(R.id.count);
        count.setText("");
        count.setText("" + correctCout + "");

        Button raiting = findViewById(R.id.raiting);
        Button newGame = findViewById(R.id.new_game);

        raiting.setOnClickListener(v -> goToRaiting());
        newGame.setOnClickListener(v -> goToGameActivity());
    }

    private void goToRaiting() {
        Intent intent = new Intent(FinishActivity.this, LeaderboardActivity.class);
        startActivity(intent);
    }

    private void goToGameActivity() {
        Intent intent = new Intent(FinishActivity.this, GameActivity.class);
        startActivity(intent);
    }
}
