package com.example.erudit;

import android.app.Application;

import com.example.erudit.Modals.Player;

public class UserObject extends Application {
    private Player player;
    @Override
    public void onCreate() {
        super.onCreate();
        player = new Player(5l, "", 0);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void clearPlayer() {
        this.player = new Player(5l, "", 0);
    }
}
