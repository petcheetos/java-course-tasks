package edu.hw2.task1;

public sealed interface Expr {

    double evaluate();

    public record Constant(double value) implements Expr {

        @Override
        public double evaluate() {
            return value;
        }
    }

    public record Negate(double value) implements Expr {
        public Negate(Expr expression) {
            this(expression.evaluate());
        }

        @Override
        public double evaluate() {
            return -value;
        }
    }

    public record Addition(double left, double right) implements Expr {
        public Addition(Expr leftExpression, Expr rightExpression) {
            this(leftExpression.evaluate(), rightExpression.evaluate());
        }

        @Override
        public double evaluate() {
            return left + right;
        }

    }

    public record Multiplication(double left, double right) implements Expr {
        public Multiplication(Expr leftExpression, Expr rightExpression) {
            this(leftExpression.evaluate(), rightExpression.evaluate());
        }

        @Override
        public double evaluate() {
            return left * right;
        }
    }

    public record Exponent(double base, double exponent) implements Expr {
        public Exponent(Expr base, Expr exponent) {
            this(base.evaluate(), exponent.evaluate());
        }

        public Exponent(Expr base, double exponent) {
            this(base.evaluate(), exponent);
        }

        @Override
        public double evaluate() {
            return Math.pow(base, exponent);
        }
    }
}
