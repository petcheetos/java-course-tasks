package edu.project4.model;

public record Point(double x, double y) {

    public Point rotatePoint(double theta) {
        double x = x() * Math.cos(theta) - y() * Math.sin(theta);
        double y = x() * Math.sin(theta) + y() * Math.cos(theta);
        return new Point(x, y);
    }

    public Point affineTransformPoint(Coefficient coefficient) {
        double x = coefficient.a() * x() + coefficient.b() * y() + coefficient.c();
        double y = coefficient.d() * x() + coefficient.e() * y() + coefficient.f();
        return new Point(x, y);
    }
}
