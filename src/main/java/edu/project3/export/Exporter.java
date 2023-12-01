package edu.project3.export;

import java.nio.file.Path;
import java.util.Map;

public interface Exporter {

    void writeToFile(String content, Path outputPath);

    String convertGeneralInfo(Map<String, String> generalMetrics);

    String convertResourcesRequested(Map<String, String> generalMetrics);

    String convertResponseCodeMetrics(Map<String, String> generalMetrics);
}
