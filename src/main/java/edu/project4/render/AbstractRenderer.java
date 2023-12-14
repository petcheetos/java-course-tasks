package edu.project4.render;

import edu.project4.model.Coefficient;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractRenderer implements Renderer {

    public void executeIteration(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        short start,
        int iterPerSample,
        int symmetry, Coefficient[] coefficients
    ) {
        Point pw = world.getRandomPoint();
        for (short step = start; step < iterPerSample; step++) {
            Coefficient coefficient = coefficients[ThreadLocalRandom.current().nextInt(0, coefficients.length)];
            pw = pw.affineTransformPoint(coefficient);
            Transformation transformation =
                variations.get(ThreadLocalRandom.current().nextInt(0, variations.size()));
            pw = transformation.apply(pw);
            if (step >= 0) {
                double theta = 0.0;
                for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, s++) {
                    Point pwr = pw.rotatePoint(theta);
                    if (world.contains(pwr)) {
                        Pixel pixel = canvas.getPixel(
                            (int) ((pwr.x() - world.x()) * canvas.getWidth() / world.width()),
                            (int) ((pw.y() - world.y()) * canvas.getHeight() / world.height())
                        );
                        if (pixel == null) {
                            continue;
                        }
                        pixel.changeColor(coefficient.color());
                    }
                }
            }
        }
    }
}
