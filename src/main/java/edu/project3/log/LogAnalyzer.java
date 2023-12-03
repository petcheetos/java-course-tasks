package edu.project3.log;

import edu.project3.ConsoleHandler;
import edu.project3.readers.FileLogsReader;
import edu.project3.readers.HTTPLogsReader;
import edu.project3.readers.LogsReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private List<Log> logsList;
    private ConsoleHandler.ConsoleCommand command;

    private LogAnalyzer() {
    }

    public LogAnalyzer(ConsoleHandler.ConsoleCommand consoleCommand) {
        command = consoleCommand;
        LogsReader logsReader;
        if (command.uri().toString().contains("http")) {
            logsReader = new HTTPLogsReader();
        } else {
            logsReader = new FileLogsReader();
        }
        String readLogs = logsReader.read(command.uri());
        LogParser parser = new LogParser();
        logsList = parser.getLogsList(readLogs);
        logsList = checkParameters(logsList);
    }

    private List<Log> checkParameters(List<Log> logs) {
        if (command.timeFrom() == null && command.timeTo() != null) {
            return LogFilter.filterLogsBeforeDate(command.timeTo(), logs);
        } else if (command.timeFrom() != null && command.timeTo() == null) {
            return LogFilter.filterLogsAfterDate(command.timeFrom(), logs);
        } else if (command.timeFrom() != null) {
            return LogFilter.filterLogsByDateRange(command.timeFrom(), command.timeTo(), logs);
        }
        return logs;
    }

    public long countTotalRequests() {
        return logsList.size();
    }

    public Map<String, Long> findMostRequestedResources() {
        Map<String, Long> resourceCounts = new HashMap<>();
        for (Log log : logsList) {
            if (log != null) {
                String requestAddress = log.requestAddress().toString();
                resourceCounts.merge(requestAddress, 1L, Long::sum);
            }
        }
        return resourceCounts;
    }

    public Map<Integer, Long> countResponseCodes() {
        Map<Integer, Long> responseCodeCounts = new HashMap<>();
        for (Log log : logsList) {
            if (log != null) {
                int responseCode = log.responseCode();
                responseCodeCounts.merge(responseCode, 1L, Long::sum);
            }
        }
        return responseCodeCounts;
    }

    public double calculateAverageResponseSize() {
        if (logsList.isEmpty()) {
            return 0.0;
        }
        long totalResponseSize = 0;
        for (Log log : logsList) {
            if (log != null) {
                totalResponseSize += log.bytes();
            }
        }
        return (double) totalResponseSize / logsList.size();
    }

    public String getPath() {
        return command.uri().getPath();
    }

    public String getStartDate() {
        if (command.timeFrom() != null) {
            return command.timeFrom().toString();
        }
        return null;
    }

    public String getEndDate() {
        if (command.timeTo() != null) {
            return command.timeTo().toString();
        }
        return null;
    }
}
