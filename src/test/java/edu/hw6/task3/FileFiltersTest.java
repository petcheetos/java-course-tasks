package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileFiltersTest {

    @Test
    void testFileFilters() {
        DirectoryStream.Filter<Path> filter = FileFilters.REGULAR_FILE
            .and(FileFilters.READABLE)
            .and(FileFilters.largerThan(39343L))
            .and(FileFilters.magicNumber(0x89, 'P', 'N', 'G'))
            .and(FileFilters.globMatches(".png"))
            .and(FileFilters.regexContains("[_]"));

        Path file = Paths.get("src", "main", "java", "edu", "hw6", "resources", "kitten_.png");

        Path path = Paths.get("src/main/java/edu/hw6/resources");
        List<Path> paths = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, filter)) {
            stream.forEach(paths::add);
            assertTrue(paths.contains(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
