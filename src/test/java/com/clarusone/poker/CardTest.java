package com.clarusone.poker;

import com.clarusone.poker.enums.Suit;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import static com.clarusone.poker.enums.Suit.CLUBS;

public class CardTest extends TestCase {

    @Test
    public void testCtor() {
        int value = 10;
        Suit suit = CLUBS;

        Card card = new Card(value, suit);

        assertEquals(10, card.getValue());
        assertEquals(suit, card.getSuit());
        assertNotNull(card.hashCode());
    }

    @Test
    public void testCompareTo_cardsEqual_comparisonFails() {
        Card card1 = new Card(5, CLUBS);
        Card card2 = new Card(5, CLUBS);

        assertEquals(-1, card1.compareTo(card2));
    }

    @Test
    public void testCompareTo_card1GreaterThan() {
        Card card1 = new Card(6, CLUBS);
        Card card2 = new Card(5, CLUBS);

        assertEquals(1, card1.compareTo(card2));
    }

    @Test
    public void testCompareTo_card1LessThan() {
        Card card1 = new Card(4, CLUBS);
        Card card2 = new Card(5, CLUBS);

        assertEquals(-1, card1.compareTo(card2));
    }

    @Test
    public void testBuilder_cardBuiltCorrectly() {
        Card card1 = new Card.Builder().build("TD");
        Card card2 = new Card.Builder().build("AH");

        assertEquals(10, card1.getValue());
        assertEquals(Suit.DIAMONDS, card1.getSuit());

        assertEquals(14, card2.getValue());
        assertEquals(Suit.HEARTS, card2.getSuit());

    }

    @Test
    public void testToString() {
        String cardString = "Card{suit=DIAMONDS, value=10}";
        Card card = new Card.Builder().build("TD");

        Assert.assertEquals(cardString, card.toString());

    }

}