package com.example.erudit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent = getIntent();
        int correctCout = intent.getIntExtra("correctCout", 0);

        TextView correctCoutText = findViewById(R.id.correct_count);
        correctCoutText.setText("Вы ответили правильно: " + correctCout + " раз");
    }
}
