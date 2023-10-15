package edu.hw1.task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayFunctionsTest {
    @Test
    @DisplayName("Testing isNestable() #1 / empty arrays")
    public void testEmptyArrays() {
        int[] first = {};
        int[] second = {};
        boolean expected = false;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }

    @Test
    @DisplayName("Testing isNestable() #2 / [1, 2, 3, 4], [0, 6], expected true")
    public void testIsNestableFunction_2() {
        int[] first = {1, 2, 3, 4};
        int[] second = {0, 6};
        boolean expected = true;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }

    @Test
    @DisplayName("Testing isNestable() #3 / [9, 9, 8], [8, 9], expected false")
    public void testIsNestableFunction_3() {
        int[] first = {9, 9, 8};
        int[] second = {8, 9};
        boolean expected = false;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }

    @Test
    @DisplayName("Testing isNestable() #4 / [3, 1], [4, 0], expected true")
    public void testIsNestableFunction_4() {
        int[] first = {3, 1};
        int[] second = {4, 0};
        boolean expected = true;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }

    @Test
    @DisplayName("Testing isNestable() #5 / [], [8, 9], expected false")
    public void testIsNestableFunction_5() {
        int[] first = {};
        int[] second = {8, 9};
        boolean expected = false;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }

    @Test
    @DisplayName("Testing isNestable() #6 / [1, 2, 3, 4], [2, 3], expected false")
    public void testIsNestableFunction_6() {
        int[] first = {};
        int[] second = {8, 9};
        boolean expected = false;
        assertEquals(ArrayFunctions.isNestable(first, second), expected);
    }
}
