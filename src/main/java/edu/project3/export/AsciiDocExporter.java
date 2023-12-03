package edu.project3.export;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class AsciiDocExporter implements Exporter {
    private final String line = "|=======================|===================|\n";
    private final String format = "| %-22s | %-17s |\n";
    private final String header = "[options=\"header\"]\n";
    private final String separator = "|===\n";

    public AsciiDocExporter() {
    }

    @Override
    public void writeToFile(String content, Path outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toString()))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String convertGeneralInfo(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[general-information]]\n");
        result.append("== General Information\n\n");
        result.append(header);
        result.append(separator);
        result.append("| Metric                 |               Value\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        result.append(separator);
        return result.toString();
    }

    @Override
    public String convertResourcesRequested(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[resources-requested]]\n");
        result.append("== Resources Requested\n\n");
        result.append(header);
        result.append(separator);
        result.append("| Resource | Value\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        result.append(separator);
        return result.toString();
    }

    @Override
    public String convertResponseCodeMetrics(Map<String, String> generalMetrics) {
        StringBuilder result = new StringBuilder();
        result.append("\n[[response-code-metrics]]\n");
        result.append("== Response Code Metrics\n\n");
        result.append(header);
        result.append(separator);
        result.append("| Code | Value\n");
        result.append(line);
        generalMetrics.forEach((key, value) -> result.append(String.format(format, key, value)));
        result.append(separator);
        return result.toString();
    }
}
