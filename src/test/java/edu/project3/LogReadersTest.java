package edu.project3;

import edu.project3.readers.FileLogsReader;
import edu.project3.readers.HTTPLogsReader;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogReadersTest {

    @Test
    void testFileLogsReader() throws URISyntaxException {
        String fileName = "fileLogs.txt";
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }

        FileLogsReader fileLogsReader = new FileLogsReader();
        assertFalse(fileLogsReader.read(new File(resource.toURI()).toURI())
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
