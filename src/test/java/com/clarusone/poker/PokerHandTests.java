package com.clarusone.poker;

import com.clarusone.poker.enums.HandResult;
import org.junit.Test;

import static com.clarusone.poker.enums.HandResult.*;
import static org.junit.Assert.*;

public class PokerHandTests {

    @Test
    public void testCtor() {
        PokerHand pokerHand = new PokerHand("2H 3H 4H 5H 6H");
        assertTrue(pokerHand.equals(pokerHand));
        assertTrue(pokerHand.equals(new PokerHand("2H 3H 4H 5H 6H")));
        assertNotNull(pokerHand.hashCode());
    }

    @Test
    public void highest_straight_flush_wins() {
        compareHands(LOSS, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }

    @Test
    public void straight_flush_beats_4_of_a_kind() {
        compareHands(WIN, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }

    @Test
    public void highest_4_of_a_kind_wins() {
        compareHands(WIN, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }

    @Test
    public void four_of_a_kind_beats_a_full_house() {
        compareHands(LOSS, "2S AH 2H AS AC", "JS JD JC JH AD");
    }

    @Test
    public void full_house_beats_a_flush() {
        compareHands(WIN, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }

    @Test
    public void highest_flush_wins() {
        compareHands(WIN, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }

    @Test
    public void flush_beats_a_straight() {
        compareHands(WIN, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
    }

    @Test
    public void two_straights_with_same_highest_card_tie() {
        compareHands(TIE, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
    }

    @Test
    public void straight_beats_three_of_a_kind() {
        compareHands(WIN, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
    }

    @Test
    public void three_of_a_kind_beats_two_pairs() {
        compareHands(LOSS, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
    }

    @Test
    public void two_pairs_beats_a_single_pair() {
        compareHands(WIN, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
    }

    @Test
    public void highest_pair_wins() {
        compareHands(LOSS, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }

    @Test
    public void highest_pair_wins_highestRemainingCards_loses() {
        compareHands(LOSS, "2S 2D AH KS QS", "9H 9C 4H 2H 3S");
    }

    @Test
    public void tie_pair_sameHands() {
        compareHands(TIE, "6S KD 7H 5S KS", "6S KD KS 7H 5S");
    }

    @Test
    public void pair_beats_a_high_card() {
        compareHands(LOSS, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
    }

    @Test
    public void lowest_card_loses() {
        compareHands(LOSS, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
    }

    @Test
    public void highest_card_wins() {
        compareHands(WIN, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
    }

    @Test
    public void equal_cards_tie() {
        compareHands(TIE, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    @Test
    public void two_full_houses_highest_three_of_a_kind_wins() {
        compareHands(WIN, "KS KH KD 2S 2C", "JS JH JD AS AC");
    }

    @Test
    public void royalFlush_beats_straightFlush_wins() {
        compareHands(WIN, "AH QH KH TH JH", "KS QS TS JS 9S");
    }
    
    private void compareHands(HandResult expectedResult, String playerHand, String opponentHand) {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        int actualResult = player.compareTo(opponent);
        assertEquals(expectedResult.getComparatorValue(), actualResult);
    }
}

