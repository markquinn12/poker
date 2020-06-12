package com.clarusone.poker.utils;

import com.clarusone.poker.Card;
import com.clarusone.poker.constants.Constants;
import com.clarusone.poker.dtos.Scores;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.clarusone.poker.constants.Constants.*;

public class ScoreEvaluator {

    private ScoreEvaluator() {
    }

    /**
     * Evaluate each hand and add to list, then return max value in the list
     * More processing needed but this allows us to change rules/base score of hands in json file.
     * Number of cards can be increased/decreased to suit rules
     */
    public static long evalHand(List<Card> cards, Scores scores) {

        cards = sortDescending(cards);

        List<Long> evaluatedScores = new ArrayList<>();
        evaluatedScores.add(evalStraightFlush(cards, scores.getScore(Constants.ROYAL_FLUSH), true));
        evaluatedScores.add(evalStraightFlush(cards, scores.getScore(Constants.STRAIGHT_FLUSH), false));
        evaluatedScores.add(evalOfAKind(cards, scores.getScore(Constants.FOUR_OF_A_KIND), 4));
        evaluatedScores.add(evalFullHouse(cards, scores.getScore(Constants.FULL_HOUSE)));
        evaluatedScores.add(evalFlush(cards, scores.getScore(Constants.FLUSH)));
        evaluatedScores.add(evalStraight(cards, scores.getScore(Constants.STRAIGHT)));
        evaluatedScores.add(evalOfAKind(cards, scores.getScore(Constants.THREE_OF_A_KIND), 3));
        evaluatedScores.add(evalTwoPair(cards, scores.getScore(Constants.TWO_PAIR)));
        evaluatedScores.add(evalOfAKind(cards, scores.getScore(Constants.PAIR), 2));
        evaluatedScores.add(evalHighCardScore(cards, scores.getScore(Constants.HIGH_CARD)));

        return Ordering.<Long>natural().max(evaluatedScores);
    }

    /**
     * Evaluate score of equal card values based on expectedMatches
     */
    private static long evalOfAKind(List<Card> cards, long baseScore, int expectedMatches) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == (expectedMatches * (expectedMatches - 1))) {
            score = (matches.get(0).getValue() * (calculatePower(cards.size()))) + evalHighCardScore(toEvaluate, baseScore);
        }
        return score;
    }

    /**
     * Calculate the power of (highest card value + 1)
     */
    private static int calculatePower(int power) {
        Integer higherCardValue = getHigherCardValue();
        return (int) Math.pow(higherCardValue, power);
    }

    private static long evalTwoPair(List<Card> cards, long baseScore) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == NUMBER_OF_MATCHES_TWO_PAIR) {
            score = (matches.get(0).getValue() * calculatePower(2)) + (matches.get(2).getValue() * calculatePower(1)) + evalHighCardScore(toEvaluate, baseScore);
        }
        return score;

    }

    private static long evalFullHouse(List<Card> cards, long baseScore) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == NUMBER_OF_MATCHES_FULL_HOUSE) {
            score = calculateFullHouseScore(cards, baseScore);
        }
        return score;

    }

    /**
     * Assumes cards sorted descending
     * Get the middle card value of full house and check if this is equal to first card value
     * If it is then then 3 of a kind value is higher than pair value
     * Score is then calculated based on above logic
     */
    private static long calculateFullHouseScore(List<Card> cards, long baseScore) {
        long score = 0;
        Card midCard = cards.get(2);
        int midValue = midCard.getValue() * calculatePower(cards.size());
        int firstCardValue = cards.get(0).getValue();

        if (firstCardValue == midCard.getValue()) {
            score += baseScore + midValue + cards.get(4).getValue();
        } else {
            score += baseScore + midValue + firstCardValue;
        }
        return score;
    }

    private static long evalStraight(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isStraight = isStraight(cards);

        if (isStraight) {
            score = evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    /**
     * Evaluate straight flush
     * If royal boolean is true, check for royal flush
     */
    private static long evalStraightFlush(List<Card> cards, long baseScore, boolean royal) {

        long score = 0;
        boolean evaluation = isStraightFlush(cards);

        //Check if the value of the top rated card equals the last value in the card map
        Integer value = Constants.getLastCardValue();

        if (!value.equals(cards.get(0).getValue()) && royal) {
            evaluation = false;
        }

        if (evaluation) {
            score = evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    private static long evalFlush(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isFlush = isFlush(cards);

        if (isFlush) {
            score = evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    /**
     * Evaluate if both straight and flush
     */
    private static boolean isStraightFlush(List<Card> cards) {
        boolean evaluation = isFlush(cards);
        if (evaluation) {
            evaluation = isStraight(cards);
        }
        return evaluation;
    }

    private static boolean isFlush(List<Card> cards) {
        boolean isFlush = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (!cards.get(i).getSuit().equals(cards.get(i + 1).getSuit())) {
                isFlush = false;
                break;
            }
        }
        return isFlush;
    }

    private static boolean isStraight(List<Card> cards) {
        boolean isStraight = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                isStraight = false;
                break;
            }
        }
        return isStraight;
    }

    /**
     * Remove all matching cards from original list to be left with list of unique cards
     */
    private static List<Card> findMatchesAndRemove(List<Card> toEvaluate) {
        List<Card> matches = matchCards(toEvaluate);
        toEvaluate.removeAll(matches);
        return matches;
    }

    /**
     * Create list of all cards that match
     */
    private static List<Card> matchCards(List<Card> toEvaluate) {
        ArrayList<Card> matches = new ArrayList<>();

        for (Card card1 : toEvaluate) {

            for (Card card2 : toEvaluate) {
                if (card1.compareTo(card2) == 0) {
                    matches.add(card1);
                }
            }
        }
        return matches;
    }

    /**
     * Evaluate the total score of all cards in list
     * Assumes cards are sorted descending
     * Card score is calculated bottom up on (Highest card value + 1) to allow for tiered scoring
     */
    private static long evalHighCardScore(List<Card> cards, long base) {

        int score = 0;
        int setValue = 1;

        for (int i = cards.size() - 1; i > -1; i--) {
            setValue += setValue * getHigherCardValue();
            score += cards.get(i).getValue() * setValue;
        }

        return base + score;

    }

    private static List<Card> sortDescending(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparing(Card::getValue).reversed()).collect(Collectors.toList());
    }

}
