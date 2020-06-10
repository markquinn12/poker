package com.clarusone.poker.enums;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SuitTest {

    @Test
    public void testGetSuit() {
        Assert.assertEquals(Suit.CLUBS, Suit.getSuit("C"));
    }
}