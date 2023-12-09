package edu.project4.transformation;

import edu.project4.model.Point;

public class Heart implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double fi = Math.atan(y / x);
        return new Point(
            r * Math.sin(r * fi),
            -r * Math.cos(r * fi)
        );
    }
}
