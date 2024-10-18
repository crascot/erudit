package com.example.erudit.Modals;

import java.util.List;

public class Question {
    private Long questionId;
    private String questionText;
    private List<Answer> answers;
    private int correct = -1;

    public Question(Long questionId, String questionText, List<Answer> answers, int correct){
        this.questionId = questionId;
        this.questionText = questionText;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestionText() {
        return questionText;
    }
    public  List<Answer> getAnswers() {
        return answers;
    }
    public int getCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + questionText + '\'' +
                ", answers=" + answers +
                ", correct=" + correct +
                '}';
    }
}