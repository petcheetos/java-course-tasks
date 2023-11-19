package edu.project3.log;

import java.util.HashMap;
import java.util.Map;

public class LogStatistics {

    private final Map<String, String> generalMetrics;
    private final Map<String, String> resourcesRequested;
    private final Map<String, String> responseCodeMetrics;
    private final LogAnalyzer logAnalyzer;

    public LogStatistics(LogAnalyzer logAnalyzer) {
        this.generalMetrics = new HashMap<>();
        this.resourcesRequested = new HashMap<>();
        this.responseCodeMetrics = new HashMap<>();
        this.logAnalyzer = logAnalyzer;
    }

    public Map<String, String> getGeneralMetrics() {
        takeGeneralInfo();
        return generalMetrics;
    }

    public Map<String, String> getResourcesRequested() {
        takeResourcesRequestedInfo();
        return resourcesRequested;
    }

    public Map<String, String> getResponseCodeMetrics() {
        takeResponseCodeMetrics();
        return responseCodeMetrics;
    }

    private void takeGeneralInfo() {
        generalMetrics.put("File", logAnalyzer.getPath());
        generalMetrics.put(
            "Start date",
            logAnalyzer.getStartDate() != null ? String.valueOf(logAnalyzer.getStartDate()) : "-"
        );
        generalMetrics.put(
            "End date",
            logAnalyzer.getEndDate() != null ? String.valueOf(logAnalyzer.getEndDate()) : "-"
        );
        generalMetrics.put("Number of requests", String.valueOf(logAnalyzer.countTotalRequests()));
        generalMetrics.put("Average response size", String.valueOf(logAnalyzer.calculateAverageResponseSize()));
    }

    private void takeResourcesRequestedInfo() {
        Map<String, Long> res = logAnalyzer.findMostRequestedResources();
        for (Map.Entry<String, Long> entry : res.entrySet()) {
            String stringValue = String.valueOf(entry.getValue());
            resourcesRequested.put(entry.getKey(), stringValue);
        }
    }

    private void takeResponseCodeMetrics() {
        Map<Integer, Long> res = logAnalyzer.countResponseCodes();
        for (Map.Entry<Integer, Long> entry : res.entrySet()) {
            String stringKey = String.valueOf(entry.getKey());
            String stringValue = String.valueOf(entry.getValue());
            responseCodeMetrics.put(stringKey, stringValue);
        }
    }
}
