package edu.project4;

import edu.project4.model.Rect;
import edu.project4.process.Corrector;
import edu.project4.render.RendererOneThread;
import edu.project4.transformation.Heart;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FractalFlameTest {

    @Test
    void testGenerate(@TempDir Path tempDir) {
        Path path = tempDir.resolve("file.png");
        FractalFlame fractalFlame = new FractalFlame(400, 400, new RendererOneThread(),
            new Rect(0, 0, 2, 2), List.of(new Heart()), new Corrector()
        );
        fractalFlame.generate(path, "png", 1000, 100, 1);
        assertTrue(Files.exists(path));
    }

    @Test
    void testMainWithIncorrectValues() throws Exception {
        withTextFromSystemIn("1920", "-1000", " 1", "1000", "100", "2", "1", "png")
            .execute(() -> {
                String output = tapSystemErr(() -> {
                    Main.main(new String[] {});
                });
                assertThat(output).contains(ConsoleManager.INVALID_VALUES);
            });
    }
}
