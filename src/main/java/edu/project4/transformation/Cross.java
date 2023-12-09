package edu.project4.transformation;

import edu.project4.model.Point;

public class Cross implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(1 / ((x * x - y * y) * (x * x - y * y)));
        return new Point(x * r, y * r);
    }
}
