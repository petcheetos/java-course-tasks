package edu.project3;

import edu.project3.readers.FileLogsReader;
import edu.project3.readers.HTTPLogsReader;
import java.io.File;
import java.net.URI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogReadersTest {

    @Test
    void testFileLogsReader() {
        FileLogsReader fileLogsReader = new FileLogsReader();
        assertFalse(fileLogsReader.read(new File("src/main/java/edu/project3/resources/fileLogs.txt").toURI())
            .isEmpty());
    }

    @Test
    void testHTTPLogsReader() {
        HTTPLogsReader httpLogsReader = new HTTPLogsReader();
        assertFalse(httpLogsReader.read(URI.create(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"))
            .isEmpty());
    }
}
