package edu.project4.transformation;

import edu.project4.model.Point;

public class Spherical implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(x / (x * x + y * y), y / (x * x + y * y));
    }
}
