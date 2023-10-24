package edu.hw3.task4;

public class RomanNumerals {
    private final static RomanLiteral[] ROMAN_LITERALS = {
        new RomanLiteral("M", 1000), new RomanLiteral("CM", 900),
        new RomanLiteral("D", 500), new RomanLiteral("CD", 400),
        new RomanLiteral("C", 100), new RomanLiteral("XC", 90),
        new RomanLiteral("L", 50), new RomanLiteral("XL", 40),
        new RomanLiteral("X", 10), new RomanLiteral("IX", 9),
        new RomanLiteral("V", 5), new RomanLiteral("IV", 4),
        new RomanLiteral("I", 1)};

    private RomanNumerals() {
    }

    public static String convertToRoman(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        int num = number;
        int currValue = 0;
        while (num > 0) {
            if (num >= ROMAN_LITERALS[currValue].value()) {
                stringBuilder.append(ROMAN_LITERALS[currValue].letters());
                num -= ROMAN_LITERALS[currValue].value();
            } else {
                currValue++;
            }
        }
        return stringBuilder.toString();
    }

    public record RomanLiteral(String letters, int value) {
    }
}
