package edu.project4.render;

import edu.project4.model.Coefficient;
import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public class RendererOneThread extends AbstractRenderer {
    private final short start = -20;

    @Override
    public FractalImage render(
        FractalImage canvas, Rect world, List<Transformation> variations,
        int samples, int iterPerSample, int symmetry
    ) {
        Coefficient[] coefficients = new Coefficient[samples];
        for (int i = 0; i < samples; i++) {
            coefficients[i] = Coefficient.init();
        }
        for (int num = 0; num < samples; num++) {
            executeIteration(canvas, world, variations, start, iterPerSample, symmetry, coefficients);
        }
        return canvas;
    }
}
