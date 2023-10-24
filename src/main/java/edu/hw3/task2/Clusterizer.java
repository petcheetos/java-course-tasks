package edu.hw3.task2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Clusterizer {

    private Clusterizer() {
    }

    public static String clusterize(String string) {
        List<String> clusters = new ArrayList<>();
        Deque<Character> brackets = new ArrayDeque<>();
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char currBracket = string.charAt(i);
            if (currBracket == '(') {
                newString.append(currBracket);
                brackets.push(currBracket);
            } else {
                newString.append(currBracket);
                brackets.pop();
                if (brackets.isEmpty()) {
                    clusters.add(newString.toString());
                    newString.setLength(0);
                }
            }
        }
        return clusters.toString();
    }
}
