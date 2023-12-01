package edu.project3;

import edu.project3.log.LogAnalysisExecutor;
import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogStatistics;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogAnalyzerTest {

    @Test
    void testGetGeneralMetrics() throws URISyntaxException {
        String fileName = "fileLogs.txt";
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            resource.toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("Number of requests", "4");
        map.put("Average response size", "122.5");
        map.put("End date", "-");
        map.put("Start date", "-");
        assertEquals(map.get("Number of requests"), logStatistics.getGeneralMetrics().get("Number of requests"));
        assertEquals(map.get("Average response size"), logStatistics.getGeneralMetrics().get("Average response size"));
        assertEquals(map.get("End date"), logStatistics.getGeneralMetrics().get("End date"));
        assertEquals(map.get("Start date"), logStatistics.getGeneralMetrics().get("Start date"));
    }

    @Test
    void testGetResourcesRequested() throws URISyntaxException {
        String fileName = "fileLogs.txt";
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            resource.toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("/downloads/product_1", "4");
        assertEquals(map, logStatistics.getResourcesRequested());
    }

    @Test
    void testGetResponseCodeMetrics() throws URISyntaxException {
        String fileName = "fileLogs.txt";
        URL resource = getClass().getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            resource.toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("200", "1");
        map.put("304", "3");
        assertEquals(map, logStatistics.getResponseCodeMetrics());
    }

    @Test
    void testAnalyzerExecutorMDFile(@TempDir Path tempDir) {

        Path path = tempDir.resolve("file.md");

        ConsoleHandler.ConsoleCommand command = new ConsoleHandler.ConsoleCommand(
            URI.create(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"),
            null, null, ConsoleHandler.ConsoleCommand.ResultFileFormat.Markdown
        );

        LogAnalysisExecutor.run(command, path);
        assertTrue(Files.exists(path));
        assertAll(
            () -> assertTrue(Files.exists(path)),
            () -> assertNotNull(Files.readAllLines(path))
        );
    }

    @Test
    void testAnalyzerExecutorADocFile(@TempDir Path tempDir) {
        Path path = tempDir.resolve("file.adoc");

        ConsoleHandler.ConsoleCommand command = new ConsoleHandler.ConsoleCommand(
            URI.create(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"),
            null, null, ConsoleHandler.ConsoleCommand.ResultFileFormat.ADoc
        );

        LogAnalysisExecutor.run(command, path);
        assertTrue(Files.exists(path));
        assertAll(
            () -> assertTrue(Files.exists(path)),
            () -> assertNotNull(Files.readAllLines(path))
        );
    }
}
