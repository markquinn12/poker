package com.clarusone.poker;

import com.clarusone.poker.dtos.ComparisonResult;
import com.clarusone.poker.services.ScoreEvaluatorService;
import com.clarusone.poker.utils.SplitterUtils;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

public class PokerHand implements Comparable<PokerHand> {

    private List<Card> cards = new ArrayList<>();

    public PokerHand(String fiveCards) {
        List<String> splitCards = SplitterUtils.splitOnSpace(fiveCards);

        for (String card : splitCards) {
            cards.add(new Card.Builder().build(card));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int compareTo(PokerHand opponentHand) {

        ComparisonResult comparisonResult = ScoreEvaluatorService.compareHands(this, opponentHand);
        return comparisonResult.getResult().getComparatorValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand pokerHand = (PokerHand) o;
        return Objects.equal(cards, pokerHand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
