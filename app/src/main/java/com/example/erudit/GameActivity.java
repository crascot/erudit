package com.example.erudit;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erudit.Modals.Answer;
import com.example.erudit.Modals.Question;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private TextView question;
    private RadioGroup radioGroup;
    private RelativeLayout relativeLayout;
    private Button buttonStart;
    private ApiClient apiClient;
    private Question questionModel;
    private int correctCout = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        apiClient = new ApiClient();

        relativeLayout = findViewById(R.id.progressBar);

        question = findViewById(R.id.question);
        radioGroup = findViewById(R.id.answers);

        buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(v -> setAnswer());

        GetQuestionFromServer();
    }

    private void GetQuestionFromServer() {
        relativeLayout.setVisibility(View.VISIBLE);
        question.setText("");
        radioGroup.removeAllViews();
        apiClient.getQuestion(GameActivity.this, new ApiClient.ApiCallback<Question>() {
            @Override
            public void onSuccess(Question result) throws URISyntaxException {
                questionModel = result;
                question.setText(result.getQuestionText());

                for(Answer answer: result.getAnswers()) {
                    RadioButton radioButton = new RadioButton(GameActivity.this);
                    radioButton.setId(answer.getIdAnswer());
                    radioButton.setText(answer.getAnswer());
                    radioButton.setPadding(0, 60, 0, 60);

                    radioGroup.addView(radioButton);
                }

                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(GameActivity.this, "Ошибка при получении ответа", Toast.LENGTH_SHORT).show();
                System.out.println("Ошибка при получении ответа: " + t.getMessage());
            }
        });
    }

    void setAnswer() {
        RadioGroup radioGroup = findViewById(R.id.answers);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = radioGroup.findViewById(selectedId);
            if (selectedRadioButton != null) {
                selectedRadioButton.setEnabled(false);
                selectedRadioButton.setChecked(false);
            }
        }

        if(selectedId == questionModel.getCorrect()) {
            Toast.makeText(this, "Правильно, ожидайте следующий вопрос", Toast.LENGTH_SHORT).show();
            incrementCorrectCout();
            GetQuestionFromServer();

        } else {
            Toast.makeText(this, "Ответ неправильный", Toast.LENGTH_SHORT).show();
//            goToFinishActivity();
        }
    }

    private void goToFinishActivity() {
        Intent intent = new Intent(GameActivity.this, FinishActivity.class);
        intent.putExtra("correctCout", correctCout);
        startActivity(intent);
    }

    public void incrementCorrectCout() {
        correctCout += 1;
    }
}
