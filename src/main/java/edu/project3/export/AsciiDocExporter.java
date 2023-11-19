package edu.project3.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AsciiDocExporter {
    private static final String LINE = "|=======================|===================|\n";
    private static final String FORMAT = "| %-22s | %-17s |\n";
    private static final String HEADER = "[options=\"header\"]\n";
    private static final String SEPARATOR = "|===\n";

    private AsciiDocExporter() {
    }

    public static void writeAsciiDocToFile(String content, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public static String convertGeneralInfo(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[general-information]]\n");
        result.append("== General Information\n\n");
        result.append(HEADER);
        result.append(SEPARATOR);
        result.append("| Metric                 |               Value\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        result.append(SEPARATOR);
        return result.toString();
    }

    public static String convertResourcesRequested(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[resources-requested]]\n");
        result.append("== Resources Requested\n\n");
        result.append(HEADER);
        result.append(SEPARATOR);
        result.append("| Resource | Value\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        result.append(SEPARATOR);
        return result.toString();
    }

    public static String convertResponseCodeMetrics(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[response-code-metrics]]\n");
        result.append("== Response Code Metrics\n\n");
        result.append(HEADER);
        result.append(SEPARATOR);
        result.append("| Code | Value\n");
        result.append(LINE);

        generalMetrics.forEach((key, value) -> result.append(String.format(FORMAT, key, value)));

        result.append(SEPARATOR);
        return result.toString();
    }
}
