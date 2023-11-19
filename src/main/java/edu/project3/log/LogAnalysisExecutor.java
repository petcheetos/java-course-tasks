package edu.project3.log;

import edu.project3.ConsoleHandler;
import edu.project3.export.AsciiDocExporter;
import edu.project3.export.MarkdownExporter;

public class LogAnalysisExecutor {

    private LogAnalysisExecutor() {
    }

    public static void run(ConsoleHandler.ConsoleCommand command) {
        LogAnalyzer logAnalyzer = new LogAnalyzer(command);
        LogStatistics logStatistics = new LogStatistics(logAnalyzer);

        export(logStatistics, command.format(),
            "src/main/java/edu/project3/resources/file.adoc"
        );
    }

    private static void export(
        LogStatistics logStatistics,
        ConsoleHandler.ConsoleCommand.ResultFileFormat resultFileFormat, String path
    ) {
        if (resultFileFormat == ConsoleHandler.ConsoleCommand.ResultFileFormat.ADoc) {
            String generalRes = AsciiDocExporter.convertGeneralInfo(logStatistics.getGeneralMetrics());
            String resourcesRequestedRes =
                AsciiDocExporter.convertResourcesRequested(logStatistics.getResourcesRequested());
            String responseCodeMetricsRes =
                AsciiDocExporter.convertResponseCodeMetrics(logStatistics.getResponseCodeMetrics());
            AsciiDocExporter.writeAsciiDocToFile(generalRes + resourcesRequestedRes + responseCodeMetricsRes, path);

        } else {
            String generalRes = MarkdownExporter.convertGeneralInfo(logStatistics.getGeneralMetrics());
            String resourcesRequestedRes =
                MarkdownExporter.convertResourcesRequested(logStatistics.getResourcesRequested());
            String responseCodeMetricsRes =
                MarkdownExporter.convertResponseCodeMetrics(logStatistics.getResponseCodeMetrics());
            MarkdownExporter.writeMarkdownToFile(generalRes + resourcesRequestedRes + responseCodeMetricsRes, path);
        }
    }
}
