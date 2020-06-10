package com.clarusone.poker.utils;

import com.clarusone.poker.dtos.Scores;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JsonFileReaderTest {

    @Test
    public void testGetFile_success() {
        Scores scores = JsonFileReader.getScores();

        assertEquals(10, scores.getHandScores().size());
        assertEquals(1000000, scores.getHandScores().get(0).getScore());
        assertEquals("HIGH_CARD", scores.getHandScores().get(0).getHand());
    }

}