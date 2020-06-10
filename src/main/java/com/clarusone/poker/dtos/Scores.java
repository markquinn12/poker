package com.clarusone.poker.dtos;

import java.util.List;

public class Scores {
    private List<HandScore> handScores;

    public List<HandScore> getHandScores() {
        return handScores;
    }

    public void setHandScores(List<HandScore> handScores) {
        this.handScores = handScores;
    }

    public long getScore(String handName) {
        return handScores.stream().filter(score -> handName.equals(score.getHand())).findFirst().map(HandScore::getScore).orElse(0L);
    }

}
