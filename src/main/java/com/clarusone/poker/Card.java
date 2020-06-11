package com.clarusone.poker;

import com.clarusone.poker.constants.Constants;
import com.clarusone.poker.enums.Suit;
import com.google.common.base.Objects;
import com.google.common.base.Splitter;

import java.util.List;

public class Card implements Comparable<Card> {

    private final Suit suit;
    private final int value;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    /**
     * If comparing to itself then return -1
     */
    @Override
    public int compareTo(Card toCompare) {
        int comparison = 0;

        if (this.value > toCompare.value) {
            comparison = 1;
        }
        if (this.value < toCompare.value || this.equals(toCompare)) {
            comparison = -1;
        }

        return comparison;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value &&
                suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(suit, value);
    }

    /**
     * Card builder to allow string to be built as Card easily
     */
    public static class Builder {

        public Card build(String card) {
            List<String> split = Splitter
                    .fixedLength(1)
                    .splitToList(card);

            return new Card(Constants.cardValues.get(split.get(0)), Suit.getSuit(split.get(1)));
        }

    }
}
