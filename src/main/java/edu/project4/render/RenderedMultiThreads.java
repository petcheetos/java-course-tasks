package edu.project4.render;

import edu.project4.model.Coefficient;
import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RenderedMultiThreads extends AbstractRenderer {
    private final short start = -20;
    private final int threads;
    private final int millisecondsToAwait = 1000;

    public RenderedMultiThreads(int threads) {
        this.threads = threads;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            Coefficient[] coefficients = new Coefficient[samples];
            for (int i = 0; i < samples; i++) {
                coefficients[i] = Coefficient.init();
            }
            for (int num = 0; num < samples; num++) {
                executorService.execute(() -> executeIteration(
                    canvas,
                    world,
                    variations,
                    start,
                    iterPerSample,
                    symmetry,
                    coefficients
                ));
            }
            executorService.awaitTermination(millisecondsToAwait, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return canvas;
    }
}
