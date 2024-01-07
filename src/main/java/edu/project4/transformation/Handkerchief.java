package edu.project4.transformation;

import edu.project4.model.Point;

public class Handkerchief implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan(x / y);
        return new Point(r * Math.sin(theta + r), r * Math.cos(theta - r));
    }
}
