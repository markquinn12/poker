package com.clarusone.poker.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum Suit {
    CLUBS("C"),
    DIAMONDS("D"),
    HEARTS("H"),
    SPADES("S");

    public final String character;
    private static final Map<String, Suit> MAP;

    Suit(String suitChar) {
        this.character = suitChar;
    }

    /**
     * Expose Enum values as immutable map
     */
    static {
        Map<String, Suit> suitCharacterMap = Arrays.stream(values())
                .collect(toMap(cg -> cg.character, e -> e));
        MAP = Collections.unmodifiableMap(suitCharacterMap);
    }

    /**
     * Get SUIT based on string value
     */
    public static Suit getSuit(final String name) {
        return MAP.get(name);
    }
}
