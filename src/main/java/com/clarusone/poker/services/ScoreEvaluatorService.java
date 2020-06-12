package com.clarusone.poker.services;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.dtos.ComparisonResult;
import com.clarusone.poker.dtos.Scores;
import com.clarusone.poker.utils.JsonFileReader;
import com.clarusone.poker.utils.ScoreEvaluator;

public class ScoreEvaluatorService {

    private ScoreEvaluatorService() {
    }

    public static ComparisonResult compareHands(PokerHand hand, PokerHand opponentHand) {

        //Read scores from file. This allows us to change the base score per hand
        Scores scores = JsonFileReader.getScores();

        long handValue = ScoreEvaluator.evalHand(hand.getCards(), scores);
        long opponentValue = ScoreEvaluator.evalHand(opponentHand.getCards(), scores);

        return new ComparisonResult(handValue, opponentValue);
    }

}
