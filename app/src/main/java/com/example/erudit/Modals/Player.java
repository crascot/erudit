package com.example.erudit.Modals;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Player implements Parcelable {
    int id;
    String name;
    int score;
    Boolean capitan;

    protected Player(Parcel in) {
        id = in.readInt();
        name = in.readString();
        score = in.readInt();
        byte tmpCapitan = in.readByte();
        capitan = tmpCapitan == 0 ? null : tmpCapitan == 1;
    }

    public String getName() {
        return name;
    }

    public void setCapitan(Boolean capitan) {
        this.capitan = capitan;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(score);
        dest.writeByte((byte) (capitan == null ? 0 : capitan ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
