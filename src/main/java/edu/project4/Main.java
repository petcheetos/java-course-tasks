package edu.project4;

import edu.project4.model.Rect;
import edu.project4.process.Corrector;
import edu.project4.render.RenderedMultiThreads;
import edu.project4.render.Renderer;
import edu.project4.render.RendererOneThread;
import edu.project4.transformation.Transformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int width = ConsoleManager.inputWidth(scanner);
            int height = ConsoleManager.inputHeight(scanner);
            ConsoleManager.choseTransformation();
            scanner.nextLine();
            String numbers = scanner.nextLine();
            String[] variants = numbers.split(ConsoleManager.SPACE);
            List<Transformation> transformations = new ArrayList<>();
            for (int i = 0; i < variants.length; i++) {
                if (!variants[i].isEmpty()) {
                    transformations.add(FractalFlame.TRANSFORMATION_MAP.get(Integer.parseInt(variants[i])));
                }
            }
            int samples = ConsoleManager.inputSamples(scanner);
            int iterPerSample = ConsoleManager.inputIterPerSample(scanner);
            int symmetry = ConsoleManager.inputSymmetry(scanner);
            int threads = ConsoleManager.inputNumberOfThreads(scanner);

            if ((width <= 0) || (height <= 0) || (samples <= 0) || (iterPerSample <= 0)
                || (symmetry <= 0) || (threads <= 0)) {
                System.err.println(ConsoleManager.INVALID_VALUES);
                return;
            }

            String format = ConsoleManager.inputFormat(scanner);
            Renderer renderer;
            if (threads > 1) {
                renderer = new RenderedMultiThreads(threads);
            } else {
                renderer = new RendererOneThread();
            }
            FractalFlame fractalFlame =
                new FractalFlame(width, height, renderer, new Rect(-4, -3, 8, 6),
                    transformations, new Corrector()
                );
            Path path = Path.of("src/main/resources/fractal." + format);
            fractalFlame.generate(path, format, samples, iterPerSample, symmetry);
        }
    }
}
