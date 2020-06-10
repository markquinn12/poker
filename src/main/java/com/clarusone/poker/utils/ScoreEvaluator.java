package com.clarusone.poker.utils;

import com.clarusone.poker.Card;
import com.clarusone.poker.constants.Constants;
import com.clarusone.poker.dtos.Scores;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreEvaluator {

    private ScoreEvaluator() {
    }

    public static long evalHand(List<Card> cards, Scores scores) {
        long score = 0;
        cards = sortDescending(cards);
        score += evalRoyalFlush(cards, scores.getScore(Constants.ROYAL_FLUSH));
        score += evalStraightFlush(cards, scores.getScore(Constants.STRAIGHT_FLUSH));
        score += evalMatches(cards, scores.getScore(Constants.FOUR_OF_A_KIND), 4);
        score += evalFullHouse(cards, scores.getScore(Constants.FULL_HOUSE), 8);
        score += evalFlush(cards, scores.getScore(Constants.FLUSH));
        score += evalStraight(cards, scores.getScore(Constants.STRAIGHT));
        score += evalMatches(cards, scores.getScore(Constants.THREE_OF_A_KIND), 3);
        score += evalTwoPair(cards, scores.getScore(Constants.TWO_PAIR), 4);
        score += evalMatches(cards, scores.getScore(Constants.PAIR), 2);

        if (score == 0) {
            score += evalHighCardScore(cards, scores.getScore(Constants.HIGH_CARD));
        }

        return score;
    }

    public static long evalMatches(List<Card> cards, long baseScore, int numMatches) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == (numMatches * (numMatches - 1))) {
            score = baseScore + (matches.get(0).getValue() * baseScore) + evalHighCardScore(toEvaluate, 0);
        }
        return score;
    }

    /**
     * Evaluate the total score of all cards in list
     */
    public static long evalTwoPair(List<Card> cards, long baseScore, int numMatches) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == numMatches) {
            score = baseScore + (matches.get(0).getValue() * baseScore) + (matches.get(2).getValue() * baseScore) + evalHighCardScore(toEvaluate, baseScore);
        }
        return score;

    }

    /**
     * Evaluate the total score of all cards in list
     */
    public static long evalFullHouse(List<Card> cards, long baseScore, int numMatches) {

        long score = 0;
        List<Card> toEvaluate = new ArrayList<>(cards);
        List<Card> matches = findMatchesAndRemove(toEvaluate);

        if (matches.size() == numMatches) {
            score = baseScore + (matches.get(0).getValue() * baseScore) + (matches.get(2).getValue() * baseScore) + evalHighCardScore(cards, baseScore);
        }
        return score;

    }

    public static long evalStraight(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isStraight = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                isStraight = false;
            }
        }

        if (isStraight) {
            score = (baseScore * cards.size()) + evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    public static long evalRoyalFlush(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isRoyalFlush = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card toCompare = cards.get(i + 1);

            if (!card.getSuit().equals(toCompare.getSuit()) &&
                    card.getValue() - toCompare.getValue() != 1) {
                isRoyalFlush = false;
            }
        }

        Integer value = Constants.getLastCardValue();

        if (!value.equals(cards.get(0))) {
            isRoyalFlush = false;
        }

        if (isRoyalFlush) {
            score = (baseScore * cards.size()) + evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    public static long evalFlush(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isFlush = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            if (!cards.get(i).getSuit().equals(cards.get(i + 1).getSuit())) {
                isFlush = false;
            }
        }

        if (isFlush) {
            score = (baseScore * cards.size()) + evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    public static long evalStraightFlush(List<Card> cards, long baseScore) {

        long score = 0;
        boolean isStraightFlush = true;

        for (int i = 0; i < cards.size() - 1; i++) {
            Card card = cards.get(i);
            Card toCompare = cards.get(i + 1);

            if (!card.getSuit().equals(toCompare.getSuit()) ||
                    card.getValue() - toCompare.getValue() != 1) {
                isStraightFlush = false;
            }
        }

        if (isStraightFlush) {
            score = (baseScore * cards.size()) + evalHighCardScore(cards, baseScore);
        }

        return score;
    }

    private static List<Card> findMatchesAndRemove(List<Card> toEvaluate) {
        List<Card> matches = matchCards(toEvaluate);
        toEvaluate.removeAll(matches);
        return matches;
    }

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
     */
    private static int evalHighCardScore(List<Card> cards, long base) {

        int score = 0;

        for (Card card : cards) {
            base = base / 100;
            score += card.getValue() + base;
        }

        return score;

    }

    private static List<Card> sortDescending(List<Card> cards) {
        return cards.stream().sorted(Comparator.comparing(Card::getValue).reversed()).collect(Collectors.toList());
    }

//    private static List<Card> sortAscending(List<Card> cards) {
//        return cards.stream().sorted(Comparator.comparing(Card::getValue)).collect(Collectors.toList());
//    }

}
