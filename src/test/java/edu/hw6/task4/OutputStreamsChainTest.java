package edu.hw6.task4;

import edu.hw6.task4.OutputStreamsChain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStreamsChainTest {

    @Test
    void testOutputStreamChain() throws IOException {
        File file = new File("src/main/java/edu/hw6/resources/text.txt");
        String msg = "Programming is learned by writing programs. ― Brian Kernighan";
        OutputStreamsChain.write(msg);

        String content = Files.readString(file.toPath());
        assertEquals(msg, content.trim());

        clearFile(file.toPath());
    }

    private void clearFile(Path path) {
        try (BufferedWriter bf = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
