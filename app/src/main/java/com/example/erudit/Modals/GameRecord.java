package com.example.erudit.Modals;

public class GameRecord {
    private Long playerId;
    private int score;

    public GameRecord(Long playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }
    public Long getPlayerId() {
        return playerId;
    }
    public int getScore() {
        return score;
    }
}
