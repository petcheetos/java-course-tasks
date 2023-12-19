package edu.hw10.task2;

public class Fibonacci implements FibCalculator {

    @Override
    public long fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
