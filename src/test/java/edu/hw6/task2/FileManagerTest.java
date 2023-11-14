package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileManagerTest {

    @Test
    void cloneFileTest() throws IOException {

        assertThrows(
            NullPointerException.class,
            () -> FileManager.cloneFile(null)
        );

        FileManager.cloneFile(Path.of("src/main/java/edu/hw6/task2/file.txt"));
        assertTrue(Files.exists(
            Path.of("src/main/java/edu/hw6/task2/file — копия.txt")
        ));

        FileManager.cloneFile(Path.of("src/main/java/edu/hw6/task2/file.txt"));
        assertTrue(Files.exists(
            Path.of("src/main/java/edu/hw6/task2/file — копия (2).txt")
        ));

        Files.delete(Path.of("src/main/java/edu/hw6/task2/file — копия.txt"));
        Files.delete(Path.of("src/main/java/edu/hw6/task2/file — копия (2).txt"));
    }
}
