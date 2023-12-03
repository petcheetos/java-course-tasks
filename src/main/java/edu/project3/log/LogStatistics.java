package edu.project3.log;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class LogStatistics {
    private final TreeMap<String, String> generalMetrics;
    private final TreeMap<String, String> resourcesRequested;
    private final TreeMap<String, String> responseCodeMetrics;
    private final LogAnalyzer logAnalyzer;

    public LogStatistics(LogAnalyzer logAnalyzer) {
        this.generalMetrics = new TreeMap<>();
        this.resourcesRequested = new TreeMap<>();
        this.responseCodeMetrics = new TreeMap<>();
        this.logAnalyzer = logAnalyzer;
    }

    public Map<String, String> getGeneralMetrics() {
        takeGeneralInfo();
        return Collections.unmodifiableSortedMap(generalMetrics);
    }

    public Map<String, String> getResourcesRequested() {
        takeResourcesRequestedInfo();
        return Collections.unmodifiableSortedMap(resourcesRequested);
    }

    public Map<String, String> getResponseCodeMetrics() {
        takeResponseCodeMetrics();
        return Collections.unmodifiableSortedMap(responseCodeMetrics);
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
