package edu.hw3.task7;

import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TreeMapComparatorTest {

    @Test
    void testTreeMapComparator() {
        TreeMap<String, String> tree = new TreeMap<>(new TreeMapComparator());
        tree.put("first", "abd");
        tree.put("second", "abc");
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    void testTreeMapComparatorExpectedNullAsFirstKey() {
        TreeMap<String, String> tree = new TreeMap<>(new TreeMapComparator());
        tree.put("first", "abd");
        tree.put("second", "abc");
        tree.put(null, "test");
        assertNull(tree.firstKey());
    }
}
