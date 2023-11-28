package edu.project3;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHandler {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final Logger LOGGER = LogManager.getLogger();
    private static final String ERROR = "Incorrect arguments";
    private final String[] arguments;

    ConsoleHandler(String[] args) {
        arguments = args;
    }

    public ConsoleCommand getCommand() {
        final String markdownFormat = "markdown";
        final String adocFormat = "adoc";
        URI pathUri = null;
        String from = null;
        String to = null;
        String format = markdownFormat;
        int argIndex = 0;
        while (argIndex < arguments.length) {
            String arg = arguments[argIndex];
            switch (arg) {
                case "--path":
                    try {
                        pathUri = new URI(arguments[++argIndex]);
                    } catch (URISyntaxException exception) {
                        try {
                            pathUri = new File(arguments[argIndex]).toURI();
                            if (!(new File(pathUri).exists())) {
                                throw new NullPointerException();
                            }
                        } catch (NullPointerException e) {
                            LOGGER.error(ERROR);
                            return null;
                        }
                    }
                    break;
                case "--from":
                    from = arguments[++argIndex];
                    break;
                case "--to":
                    to = arguments[++argIndex];
                    break;
                case "--format":
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
        if (format != null && !format.equals(markdownFormat)) {
            if (format.equals(adocFormat)) {
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
                LOGGER.error(ConsoleHandler.ERROR);
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
