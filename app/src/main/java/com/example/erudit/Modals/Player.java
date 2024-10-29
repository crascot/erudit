package com.example.erudit.Modals;

import java.io.Serializable;

public class Player implements Serializable {
    Long id;
    String name;
    int score;
    public Player(Long id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return "{"
                + "\"id\":" + id + ","
                + "\"name\":\"" + name + "\","
                + "\"score\":" + score
                + "}";
    }
}