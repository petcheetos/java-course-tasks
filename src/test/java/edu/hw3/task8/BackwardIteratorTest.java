package edu.hw3.task8;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BackwardIteratorTest {

    @Test
    void testBackwardIterator() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of(1, 2, 3, 4, 5));
        Integer[] iteratorOrder = new Integer[5];
        int counter = 0;
        while (iterator.hasNext()) {
            iteratorOrder[counter] = (Integer) iterator.next();
            counter++;
        }
        assertArrayEquals(iteratorOrder, new Integer[] {5, 4, 3, 2, 1});
    }

    @Test
    void testBackwardIteratorExpectedException() {
        BackwardIterator<Integer> iterator = new BackwardIterator<>(List.of());
        assertThatThrownBy(iterator::next)
            .isInstanceOf(NoSuchElementException.class);
    }
}
