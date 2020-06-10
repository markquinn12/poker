package com.clarusone.poker.dtos;

import com.clarusone.poker.constants.Constants;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ScoresTest {

    @Test
    public void test_oneScore_getCorrectScore() {

        long score = 10L;
        String hand = Constants.FOUR_OF_A_KIND;

        List<HandScore> handScores = Arrays.asList(createHandScore(score, hand));

        Scores scores = new Scores();
        scores.setHandScores(handScores);

        Assert.assertEquals(score, scores.getScore(hand));
        Assert.assertEquals(1, scores.getHandScores().size());

    }

    @Test
    public void test_twoScores_getCorrectScore() {

        long score = 15L;
        String hand = Constants.FULL_HOUSE;

        List<HandScore> handScores = Arrays.asList(createHandScore(10L, Constants.FOUR_OF_A_KIND),
                createHandScore(score, hand));

        Scores scores = new Scores();
        scores.setHandScores(handScores);

        Assert.assertEquals(score, scores.getScore(hand));
        Assert.assertEquals(2, scores.getHandScores().size());

    }

    private HandScore createHandScore(long score, String hand){
        HandScore handScore = new HandScore();
        handScore.setHand(hand);
        handScore.setScore(score);
        return handScore;
    }

}