package edu.project3.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MarkdownExporter {
    private static final String LINE = "|:-----------------------|:------------------:|\n";
    private static final String FORMAT = "| %-22s | %-17s |\n";

    private MarkdownExporter() {
    }

    public static void writeMarkdownToFile(String content, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static String convertGeneralInfo(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### General Information\n\n");
        result.append("| Metric                 |               Value|\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        return result.toString();
    }

    public static String convertResourcesRequested(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### Resources Requested\n\n");
        result.append("| Resource                |               Value|\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        return result.toString();
    }

    public static String convertResponseCodeMetrics(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### Response Code Metrics\n\n");
        result.append("| Code                 |               Value|\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        return result.toString();
    }
}
