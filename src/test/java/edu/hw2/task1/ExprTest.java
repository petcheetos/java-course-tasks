package edu.hw2.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExprTest {

    @DisplayName("Testing Constant")
    @Test
    void testingConstantEvaluate() {
        assertEquals(1, new Expr.Constant(1).evaluate());
        assertEquals(Double.MAX_VALUE, new Expr.Constant(Double.MAX_VALUE).evaluate());
        assertEquals(Double.MIN_VALUE, new Expr.Constant(Double.MIN_VALUE).evaluate());
        assertEquals(-32.23, new Expr.Constant(-32.23).evaluate());
    }

    @DisplayName("Testing Negate")
    @Test
    void testingNegateEvaluate() {
        assertEquals(-1, new Expr.Negate(new Expr.Constant(1)).evaluate());
        assertEquals(-Double.MIN_VALUE, new Expr.Negate(new Expr.Constant(Double.MIN_VALUE)).evaluate());
        assertEquals(-Double.MAX_VALUE, new Expr.Negate(new Expr.Constant(Double.MAX_VALUE)).evaluate());
        assertEquals(-0.455, new Expr.Negate(new Expr.Constant(0.455)).evaluate());
    }

    @DisplayName("Testing Addition")
    @Test
    void testingAdditionEvaluate() {
        assertEquals(-0.33 + 1.78, new Expr.Addition(new Expr.Constant(-0.33), new Expr.Constant(1.78)).evaluate());
        assertEquals(1 + 1, new Expr.Addition(new Expr.Constant(1), new Expr.Constant(1)).evaluate());
        assertEquals(
            Double.MAX_VALUE + Double.MIN_VALUE,
            new Expr.Addition(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(Double.MIN_VALUE)).evaluate()
        );
    }

    @DisplayName("Testing Multiplication") @Test void testingMultiplicationEvaluate() {
        assertEquals(
            -0.33 * 1.78,
            new Expr.Multiplication(new Expr.Constant(-0.33), new Expr.Constant(1.78)).evaluate()
        );
        assertEquals(
            -Double.MAX_VALUE,
            new Expr.Multiplication(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(-1)).evaluate()
        );

        assertEquals(0, new Expr.Multiplication(new Expr.Constant(Double.MIN_VALUE), new Expr.Constant(0)).evaluate());
    }

    @DisplayName("Testing Exponent")
    @Test
    void testingExponentEvaluate() {
        assertEquals(0.125, new Expr.Exponent(new Expr.Constant(2), new Expr.Constant(-3)).evaluate());
        assertEquals(-8, new Expr.Exponent(new Expr.Constant(-2), new Expr.Constant(3)).evaluate());
        assertEquals(16, new Expr.Exponent(new Expr.Constant(2), new Expr.Constant(4)).evaluate());
        assertEquals(9, new Expr.Exponent(new Expr.Constant(3), 2).evaluate());
        assertEquals(
            1 / Double.MAX_VALUE,
            new Expr.Exponent(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(-1)).evaluate()
        );
        assertEquals(
            1 / Double.MAX_VALUE,
            new Expr.Exponent(new Expr.Constant(Double.MAX_VALUE), new Expr.Constant(-1)).evaluate()
        );
        assertEquals(1, new Expr.Exponent(new Expr.Constant(Double.MIN_VALUE), new Expr.Constant(0)).evaluate());
    }
}
