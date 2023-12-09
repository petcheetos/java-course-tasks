package edu.project4.transformation;

import edu.project4.model.Point;

public class Disc implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double argument = Math.PI * Math.sqrt(x * x + y * y);
        double preCalculated = (1 / Math.PI) * Math.atan(y / x);
        return new Point(preCalculated * Math.sin(argument), preCalculated * Math.cos(argument));
    }
}
