package edu.project4.transformation;

import edu.project4.model.Point;

public class Tangent implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        return new Point(Math.sin(x) / Math.cos(y), Math.tan(y));
    }
}
