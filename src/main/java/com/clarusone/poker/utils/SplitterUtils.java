package com.clarusone.poker.utils;

import com.google.common.base.Splitter;

import java.util.List;

public class SplitterUtils {

    private static final String SPACE = " ";

    private SplitterUtils() {
    }

    public static final List<String> splitOnSpace(String toSplit) {
        return Splitter
                .on(SPACE)
                .splitToList(toSplit);
    }
}
