package com.clarusone.poker.constants;

import org.junit.Assert;
import org.junit.Test;

public class ConstantsTest {

    @Test
    public void testGetLastValueOfCardValues(){
        Assert.assertEquals(14, Constants.getLastCardValue().intValue());
    }

}