package edu.project4.model;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record Coefficient(double a, double b, double c, double d, double e, double f, Color color) {
    //private static final int MAX_COLOR_VALUE = 255;
    private static final Color[] COLORS = new Color[] {
        new Color(0, 0, 255),
        new Color(70, 130, 180),
        new Color(0, 128, 128),
        new Color(25, 25, 112)
    };

    public static Coefficient init() {
        while (true) {
            double a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double c = ThreadLocalRandom.current().nextDouble(-1, 1);
            double d = ThreadLocalRandom.current().nextDouble(-1, 1);
            double e = ThreadLocalRandom.current().nextDouble(-1, 1);
            double f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if ((a * a + d * d < 1) && (b * b + e * e < 1)
                && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d))) {
                return new Coefficient(a, b, c, d, e, f, getRandomColor());
            }
        }
    }

    private static Color getRandomColor() {
//        return new Color(
//            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE),
//            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE),
//            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE)
//        );
        return COLORS[ThreadLocalRandom.current().nextInt(0, COLORS.length)];
    }
}
