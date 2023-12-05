package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class StatsCollector {

    private final ExecutorService executorService;
    private final BlockingQueue<Metric> metricsToCollect;
    private final Map<String, Statistic> results;

    public StatsCollector(int numberOfThreads) {
        metricsToCollect = new LinkedBlockingQueue<>();
        results = new ConcurrentHashMap<>();
        executorService = Executors.newFixedThreadPool(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(this::collect);
        }
    }

    public void push(String metricName, double[] array) throws InterruptedException {
        if (!executorService.isShutdown()) {
            try {
                metricsToCollect.offer(new Metric(metricName, array), 2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RejectedExecutionException();
            }
        }
    }

    public void collect() {
        while (!executorService.isShutdown()) {
            Metric metric;
            try {
                metric = metricsToCollect.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Statistic statistic = collectStatistic(metric.array);
            results.put(metric.name, statistic);
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

    public void stop() {
        executorService.shutdown();
    }

    public Map<String, Statistic> getResults() {
        return results;
    }

    public record Statistic(double sum, double average, double max, double min) {
    }

    public record Metric(String name, double[] array) {
    }
}
