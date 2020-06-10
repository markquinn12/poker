package com.clarusone.poker.services;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.dtos.ComparisonResult;
import org.junit.Assert;
import org.junit.Test;

import static com.clarusone.poker.enums.HandResult.LOSS;
import static org.junit.Assert.*;

public class ScoreEvaluatorServiceTest {

    @Test
    public void testCompareHands() {

        PokerHand hand = new PokerHand("6S AD 7H 4S AS");
        PokerHand opponentHand = new PokerHand("AH AC 5H 6H 7S");

        ComparisonResult comparisonResult = ScoreEvaluatorService.compareHands(hand, opponentHand);

        Assert.assertEquals(-1, comparisonResult.getResult().getComparatorValue());

    }

}