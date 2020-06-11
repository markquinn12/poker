package com.clarusone.poker.utils;

import com.clarusone.poker.Card;
import com.clarusone.poker.enums.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.clarusone.poker.enums.Suit.CLUBS;
import static com.clarusone.poker.enums.Suit.DIAMONDS;

public class ScoreEvaluatorTest {

    private static final int BASE_PAIR_SCORE = 1000000;

//    @Test
//    public void evalPair() {
//
//        Card card1 = createCard(4, CLUBS);
//        Card card2 = createCard(5, DIAMONDS);
//        Card card3 = createCard(6, CLUBS);
//        Card card4 = createCard(5, CLUBS);
//        Card card5 = createCard(8, CLUBS);
//
//        List<Card> cards = new ArrayList<>(Arrays.asList(card1, card2, card3, card4, card5));
//
//        long score = ScoreEvaluator.evalOfAKind(cards, BASE_PAIR_SCORE, 2);
//
//        Assert.assertEquals(6000018, score);
//    }

    private Card createCard(int value, Suit suit){
        return new Card(value, suit);
    }

}