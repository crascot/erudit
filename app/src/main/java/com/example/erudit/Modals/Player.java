package com.example.erudit.Modals;

import java.io.Serializable;

public class Player implements Serializable {
    Long id;
    String name;
    int score;
    Boolean capitan;

    public Player(Long id, String name, int score, Boolean capitan) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.capitan = capitan;
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
    public Boolean getCapitan() {
        return capitan;
    }

    public void setCapitan(Boolean capitan) {
        this.capitan = capitan;
    }
    @Override
    public String toString() {
        return "{"
                + "\"id\":" + id + ","
                + "\"name\":\"" + name + "\","
                + "\"score\":" + score + ","
                + "\"capitan\":" + capitan
                + "}";
    }
}