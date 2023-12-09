package edu.project4.transformation;

import edu.project4.model.Point;

public class Cylinder implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.y()), point.y());
    }
}
