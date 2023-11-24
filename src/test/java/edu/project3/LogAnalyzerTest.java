package edu.project3;

import edu.project3.log.LogAnalyzer;
import edu.project3.log.LogStatistics;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalyzerTest {

    @Test
    void testGetGeneralMetrics() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            new File("src/main/java/edu/project3/resources/fileLogs.txt").toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("Number of requests", "4");
        map.put("Average response size", "122.5");
        map.put("End date", "-");
        map.put("Start date", "-");
        map.put("File", "/C:/javacourse/src/main/java/edu/project3/resources/fileLogs.txt");
        assertEquals(map, logStatistics.getGeneralMetrics());
    }

    @Test
    void testGetResourcesRequested() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            new File("src/main/java/edu/project3/resources/fileLogs.txt").toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("/downloads/product_1", "4");
        assertEquals(map, logStatistics.getResourcesRequested());
    }

    @Test
    void testGetResponseCodeMetrics() {
        LogAnalyzer logAnalyzer = new LogAnalyzer(new ConsoleHandler.ConsoleCommand(
            new File("src/main/java/edu/project3/resources/fileLogs.txt").toURI(),
            null, null, null
        ));

        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Map<String, String> map = new TreeMap<>();
        map.put("200", "1");
        map.put("304", "3");
        assertEquals(map, logStatistics.getResponseCodeMetrics());
    }
}
