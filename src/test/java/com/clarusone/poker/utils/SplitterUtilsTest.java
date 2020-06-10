package com.clarusone.poker.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SplitterUtilsTest {

    @Test
    public void testSplitOnSpace() {
        String toSplit = "HELLO WORLD";

        List<String> strings = SplitterUtils.splitOnSpace(toSplit);

        Assert.assertEquals(2, strings.size());
        Assert.assertEquals("HELLO", strings.get(0));
        Assert.assertEquals("WORLD", strings.get(1));

    }

    @Test
    public void testSplitOnSpace_noSpace() {
        String toSplit = "HELLO";

        List<String> strings = SplitterUtils.splitOnSpace(toSplit);

        Assert.assertEquals(1, strings.size());
        Assert.assertEquals(toSplit, strings.get(0));
    }
}