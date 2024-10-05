package com.example.erudit.Modals;

import java.util.List;

public class Question {
    private final String question;
    private final List<Answer> answers;
    private final int correct;

    public Question(String question, List<Answer> answers, int correct){
        this.question = question;
        this.answers = answers;
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
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
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", correct=" + correct +
                '}';
    }
}