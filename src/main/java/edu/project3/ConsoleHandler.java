package edu.project3;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHandler {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static String[] arguments;
    private static final String PATH_COMMAND = "--path";
    private static final String FROM_PARAM = "--from";
    private static final String TO_PARAM = "--to";
    private static final String FORMAT_COMMAND = "--format";
    private static final String MARKDOWN_FORMAT = "markdown";
    private static final String ADOC_FORMAT = "adoc";
    private static final String ERROR = "Incorrect arguments";

    ConsoleHandler(String[] args) {
        arguments = args;
    }

    public ConsoleCommand getCommand() {
        URI pathUri = null;
        String from = null;
        String to = null;
        String format = MARKDOWN_FORMAT;
        int argIndex = 0;
        while (argIndex < arguments.length) {
            String arg = arguments[argIndex];
            switch (arg) {
                case PATH_COMMAND:
                    try {
                        pathUri = new URI(arguments[++argIndex]);
                    } catch (URISyntaxException exception) {
                        LOGGER.error(ERROR);
                        return null;
                    }
                   //pathUri = new File(arguments[++argIndex]).toURI();
                    break;
                case FROM_PARAM:
                    from = arguments[++argIndex];
                    break;
                case TO_PARAM:
                    to = arguments[++argIndex];
                    break;
                case FORMAT_COMMAND:
                    format = arguments[++argIndex];
                    break;
                default:
                    LOGGER.error(ERROR);
                    return null;
            }
            argIndex++;
        }

        LocalDate dateFrom = getDate(from);
        LocalDate dateTo = getDate(to);

        ConsoleCommand.ResultFileFormat fileFormat = ConsoleCommand.ResultFileFormat.Markdown;
        if (format != null && !format.equals(MARKDOWN_FORMAT)) {
            if (format.equals(ADOC_FORMAT)) {
                fileFormat = ConsoleCommand.ResultFileFormat.ADoc;
            }
        }
        return new ConsoleCommand(pathUri, dateFrom, dateTo, fileFormat);
    }

    private static LocalDate getDate(String string) {
        if (string != null) {
            try {
                return LocalDate.parse(string, DATE_FORMATTER);
            } catch (DateTimeParseException exception) {
                LOGGER.error(ERROR);
            }
        }
        return null;
    }

    public record ConsoleCommand(URI uri, LocalDate timeFrom, LocalDate timeTo, ResultFileFormat format) {
        public enum ResultFileFormat {
            Markdown,
            ADoc
        }
    }
}
