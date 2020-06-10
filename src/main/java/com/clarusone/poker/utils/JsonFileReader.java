package com.clarusone.poker.utils;

import com.clarusone.poker.dtos.Scores;
import com.clarusone.poker.exceptions.FileReadException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JsonFileReader {

    private JsonFileReader() {
    }

    private static final String JSON_FILE = "/HandScores.json";

    public static Scores getScores() {
        InputStream is = JsonFileReader.class.getResourceAsStream(JSON_FILE);
        try {
            return new ObjectMapper().readValue(is, Scores.class);
        } catch (IOException e) {
            throw new FileReadException("Could not read json file", e);
        }
    }

}
