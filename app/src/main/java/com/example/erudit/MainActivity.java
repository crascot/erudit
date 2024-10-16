package com.example.erudit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);

        Button btnStart = findViewById(R.id.button_start);
        Button buttonFormSubmit = findViewById(R.id.button_form_submit);
        final LinearLayout mainForm = findViewById(R.id.main_form);

        apiClient = new ApiClient();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                mainForm.setVisibility(View.VISIBLE);
            }
        });

        buttonFormSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText.getText().toString();
                if(!username.isEmpty()) {
                    apiClient.postUsername(MainActivity.this, username, new ApiClient.ApiCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                            goToGameActivity();
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Имя не должно быть пустым", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goToGameActivity() {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }
}