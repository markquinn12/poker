package com.clarusone.poker.dtos;

import com.clarusone.poker.enums.HandResult;
import org.junit.Assert;
import org.junit.Test;

public class ComparisonResultTest {

    @Test
    public void testCtor() {

        long handValue = 99L;
        long opponentHandValue = 110L;

        ComparisonResult comparisonResult = new ComparisonResult(handValue, opponentHandValue);

        Assert.assertEquals(HandResult.LOSS, comparisonResult.getResult());
    }


}