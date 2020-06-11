package com.clarusone.poker.dtos;

import com.clarusone.poker.enums.HandResult;

public class ComparisonResult {

    private long handValue;
    private long opponentHandValue;

    public ComparisonResult(long handValue, long opponentHandValue) {
        this.handValue = handValue;
        this.opponentHandValue = opponentHandValue;
    }

    public HandResult getResult(){

        HandResult result = HandResult.TIE;
        if (handValue > opponentHandValue) result = HandResult.WIN;
        if (handValue < opponentHandValue) result = HandResult.LOSS;
        return result;
    }

}
