package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.process.Corrector;
import edu.project4.process.ImageUtils;
import edu.project4.render.Renderer;
import edu.project4.transformation.Cross;
import edu.project4.transformation.Cylinder;
import edu.project4.transformation.Disc;
import edu.project4.transformation.Ex;
import edu.project4.transformation.Handkerchief;
import edu.project4.transformation.Heart;
import edu.project4.transformation.Spherical;
import edu.project4.transformation.Tangent;
import edu.project4.transformation.Transformation;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class FractalFlame {
    private final FractalImage fractalImage;
    private final Renderer renderer;
    private final Rect world;
    private final List<Transformation> transformations;
    private final Corrector corrector;
    public static final Map<Integer, Transformation> TRANSFORMATION_MAP = Map.of(
        1, new Cross(),
        2, new Cylinder(),
        3, new Disc(),
        4, new Ex(),
        5, new Handkerchief(),
        6, new Heart(),
        7, new Spherical(),
        8, new Tangent()
    );

    public FractalFlame(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> transformations,
        Corrector corrector
    ) {
        this.fractalImage = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.transformations = transformations;
        this.corrector = corrector;
    }

    public void generate(Path path, String format, int samples, int iterPerSample, int symmetry) {
        renderer.render(fractalImage, world, transformations, samples, iterPerSample, symmetry);
        if (corrector != null) {
            corrector.process(fractalImage);
        }
        try {
            ImageUtils.save(fractalImage, path, format);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
