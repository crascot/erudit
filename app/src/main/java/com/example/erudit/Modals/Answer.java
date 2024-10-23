package com.example.erudit.Modals;

public class Answer {
    int idAnswer;
    String answer;

    public Answer(int idAnswer, String answer) {
        this.idAnswer = idAnswer;
        this.answer = answer;
    }
    public int getIdAnswer() {
        return idAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}