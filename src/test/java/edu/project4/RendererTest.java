package edu.project4;

import edu.project4.model.Rect;
import edu.project4.process.Corrector;
import edu.project4.render.RenderedMultiThreads;
import edu.project4.render.RendererOneThread;
import edu.project4.transformation.Heart;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RendererTest {

    @Test
    void performanceTest(@TempDir Path tempDir) {
        Path path = tempDir.resolve("file.png");
        FractalFlame flame1 = new FractalFlame(1920, 1080, new RendererOneThread(),
            new Rect(0, 0, 2, 2), List.of(new Heart()), new Corrector()
        );
        FractalFlame flame2 = new FractalFlame(1920, 1080, new RenderedMultiThreads(4),
            new Rect(0, 0, 2, 2), List.of(new Heart()), new Corrector()
        );

        long startTime1 = System.currentTimeMillis();
        flame1.generate(path, "png", 100000, 1000, 1);
        long endTime1 = System.currentTimeMillis();
        long timeElapsed1 = endTime1 - startTime1;

        long startTime2 = System.currentTimeMillis();
        flame2.generate(path, "png", 100000, 1000, 1);
        long endTime2 = System.currentTimeMillis();
        long timeElapsed2 = endTime2 - startTime2;

        assertTrue(timeElapsed2 < timeElapsed1);
    }
}
