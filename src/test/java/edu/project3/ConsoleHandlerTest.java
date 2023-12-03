package edu.project3;

import java.net.URI;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleHandlerTest {

    @Test
    void testMainWithNoArgs() {
        assertThrows(NullPointerException.class, () -> Main.main(null));
    }

    @Test
    void testWithArgs() {
        URI uri = URI.create("src/main/java/edu/project3/resources/fileLogs.txt");
        String[] args = new String[] {"--path", uri.toString(),
            "--from", "2023-08-31", "--format", "adoc"};
        ConsoleHandler handler = new ConsoleHandler(args);
        ConsoleHandler.ConsoleCommand command = handler.getCommand();
        assertAll(
            () -> assertEquals(command.timeFrom(), LocalDate.of(2023, 8, 31)),
            () -> assertEquals(command.format(), ConsoleHandler.ConsoleCommand.ResultFileFormat.ADoc),
            () -> assertEquals(command.uri(), uri),
            () -> assertNull(command.timeTo())
        );
    }

    @Test
    void testWithIncorrectArg() {
        URI uri = URI.create("src/main/java/edu/project3/resources/fileLogs.txt");
        String[] args = new String[] {"--path", uri.toString(),
            "from", "2023-08-31", "--format", "adoc"};
        ConsoleHandler handler = new ConsoleHandler(args);
        ConsoleHandler.ConsoleCommand command = handler.getCommand();
        assertNull(command);
    }
}
