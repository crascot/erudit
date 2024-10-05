package com.example.erudit.Modals;

public class Answer {
    int id;
    String text;

    public Answer(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Answer" + "id: " + id + ", text: '" + text + '\'';
    }
}