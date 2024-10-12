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
import com.example.erudit.Modals.Player;
import com.example.erudit.Modals.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private View contentLayout;
    private TextView questionText;
    private RadioGroup radioGroup;
    private RelativeLayout relativeLayout;
    private TextView user1;
    private TextView user2;
    private TextView user3;
    private TextView user4;
    private ApiClient apiClient;
    private Player player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        apiClient = new ApiClient();

        Intent intent = getIntent();
        player = intent.getParcelableExtra("player");

        apiClient = new ApiClient();

        joinRoom();

        contentLayout = findViewById(R.id.contentLayout);
        questionText = findViewById(R.id.question);
        radioGroup = findViewById(R.id.answers);

        user1 = findViewById(R.id.user1);
        user1.setText(player.getName());

        Button buttonStart = findViewById(R.id.button_start);

        relativeLayout = findViewById(R.id.progressBar);
        buttonStart.setOnClickListener(v -> setAnswer());
    }

    public void joinRoom() {
        apiClient.joinGame(GameActivity.this, player, new ApiClient.ApiCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean isCapitan) {
                player.setCapitan(isCapitan);
                if(isCapitan) {
                    Toast.makeText(GameActivity.this, "Вы являетесь капитаном", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(GameActivity.this, "Вы присоединились", Toast.LENGTH_SHORT).show();
                }
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(GameActivity.this, "Ошибка комнаты", Toast.LENGTH_SHORT).show();
                gotoMain();
            }
        });
    }

    public void getDataFromWS(Question question) {
        runOnUiThread(() -> {
            contentLayout.setVisibility(View.VISIBLE);
            questionText.setText(question.getQuestion());

            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                View view = radioGroup.getChildAt(i);
                if (view instanceof RadioButton) {
                    RadioButton radioButton = (RadioButton) view;
                    radioButton.setText(question.getAnswers().get(i).getText());
                    radioButton.setId(question.getAnswers().get(i).getId());
                }
            }
            relativeLayout.setVisibility(View.GONE);
        });
    }

    void setAnswer() {
        Button buttonStart = findViewById(R.id.button_start);
        RadioGroup radioGroup = findViewById(R.id.answers);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        if (selectedId != -1) {
            RadioButton selectedRadioButton = radioGroup.findViewById(selectedId);
            if (selectedRadioButton != null) {
                selectedRadioButton.setEnabled(false);
                selectedRadioButton.setChecked(false);
            }
        }

        if(selectedId == 1) {
            Toast.makeText(this, "Вы ответили правильно", Toast.LENGTH_SHORT).show();
            buttonStart.setText("Продолжить?");

        } else {
            Toast.makeText(this, "Ответ неправильный", Toast.LENGTH_SHORT).show();
        }
    }
    public void getQuestion(String questionData) {
        try {
            JSONObject jsonObject = new JSONObject(questionData);
            Long questionId = jsonObject.getLong("questionId");
            String questionText = jsonObject.getString("question");
            int questionCorrect = jsonObject.getInt("correct");

            JSONArray answersArray = jsonObject.getJSONArray("answers");
            List<Answer> answerList = new ArrayList<>();

            for (int i = 0; i < answersArray.length(); i++) {
                JSONObject answerObject = answersArray.getJSONObject(i);

                int id = answerObject.getInt("id");
                String text = answerObject.getString("text");

                Answer answer = new Answer(id, text);

                answerList.add(answer);
            }

            Question question = new Question(questionId, questionText, answerList, questionCorrect);

            getDataFromWS(question);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private void gotoMain() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
