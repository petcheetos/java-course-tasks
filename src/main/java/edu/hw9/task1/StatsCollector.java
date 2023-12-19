package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector implements AutoCloseable {
    private final ExecutorService executorService;
    private final Map<String, Statistic> results;

    public StatsCollector(int numberOfThreads) {
        results = new ConcurrentHashMap<>();
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void push(String metricName, double[] array) {
        if (!executorService.isShutdown()) {
            executorService.submit(() -> {
                Statistic statistic = collectStatistic(array);
                results.put(metricName, statistic);
            });
        }
    }

    private Statistic collectStatistic(double[] array) {
        double sum = 0;
        double max = array[0];
        double min = array[0];
        for (double val : array) {
            sum += val;
            max = Math.max(val, max);
            min = Math.min(val, min);
        }
        return new Statistic(sum, sum / array.length, max, min);
    }

    public Map<String, Statistic> getResults() {
        return results;
    }

    @Override
    public void close() {
        executorService.shutdown();
    }

    public record Statistic(double sum, double average, double max, double min) {
    }

    public record Metric(String name, double[] array) {
    }
}
