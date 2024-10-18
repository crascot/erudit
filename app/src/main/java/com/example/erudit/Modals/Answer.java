package com.example.erudit.Modals;

public class Answer {
    Long id;
    int idAnswer;
    String answer;

    public Answer(Long id, int idAnswer, String answer) {
        this.id = id;
        this.idAnswer = idAnswer;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public int getIdAnswer() {
        return idAnswer;
    }

    public String getAnswer() {
        return answer;
    }
}