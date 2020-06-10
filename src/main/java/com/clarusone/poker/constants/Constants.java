package com.clarusone.poker.constants;

import java.util.LinkedHashMap;

public class Constants {

    private Constants() {
    }

    public static final String HIGH_CARD = "HIGH_CARD";
    public static final String PAIR = "PAIR";
    public static final String TWO_PAIR = "TWO_PAIR";
    public static final String THREE_OF_A_KIND = "THREE_OF_A_KIND";
    public static final String STRAIGHT = "STRAIGHT";
    public static final String FLUSH = "FLUSH";
    public static final String FULL_HOUSE = "FULL_HOUSE";
    public static final String FOUR_OF_A_KIND = "FOUR_OF_A_KIND";
    public static final String STRAIGHT_FLUSH = "STRAIGHT_FLUSH";
    public static final String ROYAL_FLUSH = "ROYAL_FLUSH";

    public static final LinkedHashMap<String, Integer> cardValues = new LinkedHashMap<String, Integer>() {{
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("T", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 14);
    }};

    public static Integer getLastCardValue(){
        return cardValues.values().stream().skip(Constants.cardValues.size() - 1).findFirst().get();
    }
}