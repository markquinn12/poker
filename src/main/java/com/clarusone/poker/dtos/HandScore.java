package com.clarusone.poker.dtos;

public class HandScore {

    private long score;
    private String hand;

    public long getScore() {
        return score;
    }

    public String getHand() {
        return hand;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }
}
