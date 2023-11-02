package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencyCounter {

    private FrequencyCounter() {
    }

    public static <T> Map<T, Integer> freqDict(List<T> objects) {
        Map<T, Integer> dictionary = new HashMap<>();
        for (T obj : objects) {
            dictionary.merge(obj, 1, Integer::sum);
        }
        return dictionary;
    }
}
