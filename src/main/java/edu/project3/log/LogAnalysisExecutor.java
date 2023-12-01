package edu.project3.log;

import edu.project3.ConsoleHandler;
import edu.project3.export.AsciiDocExporter;
import edu.project3.export.Exporter;
import edu.project3.export.MarkdownExporter;
import java.nio.file.Path;

public class LogAnalysisExecutor {

    private LogAnalysisExecutor() {
    }

    @SuppressWarnings("MultipleStringLiterals")
    public static void run(ConsoleHandler.ConsoleCommand command) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(command);
        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        Path path;

        if (command.format() == ConsoleHandler.ConsoleCommand.ResultFileFormat.ADoc) {
            path = Path.of(System.getProperty("user.dir") + "/src/main/java/edu/project3/resources/file.adoc");

        } else {
            path = Path.of(System.getProperty("user.dir") + "/src/main/java/edu/project3/resources/file.md");
        }

        export(logStatistics, command.format(), path);
    }

    public static void run(ConsoleHandler.ConsoleCommand command, Path path) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(command);
        LogStatistics logStatistics = new LogStatistics(logAnalyzer);
        export(logStatistics, command.format(), path);
    }

    private static void export(
        LogStatistics logStatistics,
        ConsoleHandler.ConsoleCommand.ResultFileFormat resultFileFormat, Path path
    ) {
        Exporter exporter;
        if (resultFileFormat == ConsoleHandler.ConsoleCommand.ResultFileFormat.ADoc) {
            exporter = new AsciiDocExporter();
        } else {
            exporter = new MarkdownExporter();
        }
        String generalRes = exporter.convertGeneralInfo(logStatistics.getGeneralMetrics());
        String resourcesRequestedRes =
            exporter.convertResourcesRequested(logStatistics.getResourcesRequested());
        String responseCodeMetricsRes =
            exporter.convertResponseCodeMetrics(logStatistics.getResponseCodeMetrics());
        exporter.writeToFile(generalRes + resourcesRequestedRes + responseCodeMetricsRes, path);
    }
}
