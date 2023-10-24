package edu.hw3.task2;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClusterizerTest {

    @Test
    void test_1() {
        String[] array = {"((()))"};
        assertEquals(Clusterizer.clusterize("((()))"), Arrays.toString(array));
    }

    @Test
    void test_2() {
        String[] array = {"()", "()", "()"};
        assertEquals(Clusterizer.clusterize("()()()"), Arrays.toString(array));
    }

    @Test
    void test_3() {
        String[] array = {"((()))", "(())", "()", "()", "(()())"};
        assertEquals(Clusterizer.clusterize("((()))(())()()(()())"), Arrays.toString(array));
    }

    @Test
    void test_4() {
        String[] array = {"((())())", "(()(()()))"};
        assertEquals(Clusterizer.clusterize("((())())(()(()()))"), Arrays.toString(array));
    }

    @Test
    void testWithEmptyString() {
        String[] array = {};
        assertEquals(Clusterizer.clusterize(""), Arrays.toString(array));
    }

    @Test
    void testWithNull() {
        String[] array = null;
        assertEquals(Clusterizer.clusterize(""), "[]");
    }
}
