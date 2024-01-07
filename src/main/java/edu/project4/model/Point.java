package edu.project4.model;

public record Point(double x, double y) {

    public Point rotatePoint(double theta) {
        double newX = x() * Math.cos(theta) - y() * Math.sin(theta);
        double newY = x() * Math.sin(theta) + y() * Math.cos(theta);
        return new Point(newX, newY);
    }

    public Point affineTransformPoint(Coefficient coefficient) {
        double newX = coefficient.a() * x() + coefficient.b() * y() + coefficient.c();
        double newY = coefficient.d() * x() + coefficient.e() * y() + coefficient.f();
        return new Point(newX, newY);
    }
}
