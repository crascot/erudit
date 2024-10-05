package com.example.erudit;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.erudit.Modals.Question;

public class GameActivity extends AppCompatActivity {
    private View contentLayout;
    private TextView questionText;
    private RadioGroup radioGroup;
    private ApiClient apiClient;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        contentLayout = findViewById(R.id.contentLayout);
        questionText = findViewById(R.id.question);
        radioGroup = findViewById(R.id.answers);

        Button buttonStart = findViewById(R.id.button_start);

        apiClient = new ApiClient();

        relativeLayout = findViewById(R.id.progressBar);

        getQuestion();

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswer();
            }
        });
    }

    private void getQuestion() {
        apiClient.getQuestion(this, new ApiClient.ApiCallback<Question>() {
            @Override
            public void onSuccess(Question question) {
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
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(GameActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
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
}
