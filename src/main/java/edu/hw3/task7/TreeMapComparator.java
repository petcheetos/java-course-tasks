package edu.hw3.task7;

import java.util.Comparator;

public class TreeMapComparator implements Comparator<String> {

    @Override
    public int compare(String firstKey, String secondKey) {
        if (firstKey == null && secondKey == null) {
            return 0;
        } else if (firstKey == null) {
            return -1;
        } else if (secondKey == null) {
            return 1;
        } else {
            return firstKey.compareTo(secondKey);
        }
    }
}

