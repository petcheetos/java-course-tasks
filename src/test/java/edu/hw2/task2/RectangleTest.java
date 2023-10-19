package edu.hw2.task2;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.*;

public class RectangleTest {

    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(10, 20)),
            Arguments.of(new Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);
    }
}
