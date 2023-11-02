package edu.hw3.task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrequencyCounterTest {

    @Test
    void testWithString_1() {
        assertEquals(
            Map.of("bb", 2, "a", 2),
            FrequencyCounter.freqDict(List.of("a", "bb", "a", "bb"))
        );
    }

    @Test
    void testWithString_2() {
        assertEquals(
            Map.of("this", 1, "and", 2, "that", 1),
            FrequencyCounter.freqDict(List.of("this", "and", "that", "and"))
        );
    }

    @Test
    void testWithString_3() {
        assertEquals(
            Map.of("код", 3, "bug", 1),
            FrequencyCounter.freqDict(List.of("код", "код", "код", "bug"))
        );
    }

    @Test
    void testWithInteger() {
        assertEquals(
            Map.of(1, 2, 2, 2),
            FrequencyCounter.freqDict(List.of(1, 1, 2, 2))
        );
    }

    @Test
    void testWithEmptyList() {
        assertEquals(
            Map.of(),
            FrequencyCounter.freqDict(List.of())
        );
    }
}
