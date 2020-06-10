package com.clarusone.poker.enums;

public enum HandResult {
    TIE(0),
    WIN(1),
    LOSS(-1);

    private final int comparatorValue;

    HandResult(int comparatorValue) {
        this.comparatorValue = comparatorValue;
    }

    public int getComparatorValue() {
        return comparatorValue;
    }
}
