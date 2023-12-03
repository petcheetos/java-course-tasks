package edu.project3;

import edu.project3.log.Log;
import edu.project3.log.LogFilter;
import edu.project3.log.LogParser;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogParseFilterTest {

    private final String logs =
        "217.168.17.5 - - [17/May/2015:08:05:34 +0000] \"GET /downloads/product_1 HTTP/1.1\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.8.10.3)\"\n217.168.17.5 - - [20/May/2015:08:05:09 +0000] \"GET /downloads/product_2 HTTP/1.1\" 200 490 \"-\" \"Debian APT-HTTP/1.3 (0.8.10.3)\"\n93.180.71.3 - - [20/Jun/2015:08:05:57 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"\n93.180.71.3 - - [27/Jun/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";

    @Test
    void testParser() {
        LogParser parser = new LogParser();
        List<Log> logList = parser.getLogsList(logs);
        assertEquals(logList, List.of
            (
                new Log(
                    "217.168.17.5",
                    " - - ",
                    LocalDateTime.of(2015, 5, 17, 8, 5, 34),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    200,
                    490,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.10.3)\""
                ),
                new Log(
                    "217.168.17.5",
                    " - - ",
                    LocalDateTime.of(2015, 5, 20, 8, 5, 9),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_2"),
                    "HTTP/1.1\"",
                    200,
                    490,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.10.3)\""
                ),
                new Log(
                    "93.180.71.3",
                    " - - ",
                    LocalDateTime.of(2015, 6, 20, 8, 5, 57),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    304,
                    0,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                ),
                new Log(
                    "93.180.71.3",
                    " - - ",
                    LocalDateTime.of(2015, 6, 27, 8, 5, 23),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    304,
                    0,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                )
            )
        );
    }

    @Test
    void testFilterLogsByDateRange() {
        LogParser parser = new LogParser();
        List<Log> logList = parser.getLogsList(logs);
        assertEquals(
            List.of(
                new Log(
                    "217.168.17.5",
                    " - - ",
                    LocalDateTime.of(2015, 5, 17, 8, 5, 34),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    200,
                    490,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.10.3)\""
                ),
                new Log(
                    "217.168.17.5",
                    " - - ",
                    LocalDateTime.of(2015, 5, 20, 8, 5, 9),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_2"),
                    "HTTP/1.1\"",
                    200,
                    490,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.10.3)\""
                ),
                new Log(
                    "93.180.71.3",
                    " - - ",
                    LocalDateTime.of(2015, 6, 20, 8, 5, 57),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    304,
                    0,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                )
            ),
            LogFilter.filterLogsByDateRange(
                LocalDate.of(2015, 5, 17),
                LocalDate.of(2015, 6, 20),
                logList
            )
        );
    }

    @Test
    void testFilterLogsAfterDate() {
        LogParser parser = new LogParser();
        List<Log> logList = parser.getLogsList(logs);
        assertEquals(
            List.of(
                new Log(
                    "93.180.71.3",
                    " - - ",
                    LocalDateTime.of(2015, 6, 27, 8, 5, 23),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    304,
                    0,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\""
                )
            ),
            LogFilter.filterLogsAfterDate(LocalDate.of(2015, 6, 25), logList)
        );
    }

    @Test
    void testFilterLogsBeforeDate() {
        LogParser parser = new LogParser();
        List<Log> logList = parser.getLogsList(logs);
        assertEquals(
            List.of(
                new Log(
                    "217.168.17.5",
                    " - - ",
                    LocalDateTime.of(2015, 5, 17, 8, 5, 34),
                    Log.RequestType.GET,
                    URI.create("/downloads/product_1"),
                    "HTTP/1.1\"",
                    200,
                    490,
                    "\"-\"",
                    "\"Debian APT-HTTP/1.3 (0.8.10.3)\""
                )
            ),
            LogFilter.filterLogsBeforeDate(LocalDate.of(2015, 5, 18), logList)
        );
    }
}
