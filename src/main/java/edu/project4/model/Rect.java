package edu.project4.model;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {

    public boolean contains(Point p) {
        return (x < p.x() && p.x() < x + width) && (y < p.y() && p.y() < y + height);
    }

    public Point getRandomPoint() {
        double newX = x() + ThreadLocalRandom.current().nextDouble(0, width);
        double newY = y() + ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(newX, newY);
    }
}
