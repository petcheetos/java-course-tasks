package edu.project3.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class MarkdownExporter implements Exporter {
    private final String line = "|:-----------------------|:------------------:|\n";
    private final String format = "| %-22s | %-17s |\n";

    public MarkdownExporter() {
    }

    @Override
    public void writeToFile(String content, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String convertGeneralInfo(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### General Information\n\n");
        result.append("| Metric                 |               Value|\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        return result.toString();
    }


    @Override
    public String convertResourcesRequested(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### Resources Requested\n\n");
        result.append("| Resource                |               Value|\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        return result.toString();
    }


    @Override
    public String convertResponseCodeMetrics(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n#### Response Code Metrics\n\n");
        result.append("| Code                 |               Value|\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        return result.toString();
    }
}
