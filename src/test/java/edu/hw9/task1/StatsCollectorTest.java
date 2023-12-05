package edu.hw9.task1;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {

    @Test
    void testCollectStats() throws InterruptedException {
        List<StatsCollector.Metric> metrics = List.of(
            new StatsCollector.Metric("one", new double[] {1.0, 2.0, 3.0}),
            new StatsCollector.Metric("two", new double[] {0.1, 0.05, 1.4, 5.1, 0.3}),
            new StatsCollector.Metric("three", new double[] {2.0, 0.0, 3.0}),
            new StatsCollector.Metric("four", new double[] {2.0, 0.0, 3.0, 4.0})
        );

        Map<String, StatsCollector.Statistic> expected = Map.of(
            "one", new StatsCollector.Statistic(6.0, 2.0, 3.0, 1.0),
            "two", new StatsCollector.Statistic(6.949999999999999, 1.39, 5.1, 0.05),
            "three", new StatsCollector.Statistic(5.0, 1.6666666666666667, 3.0, 0.0),
            "four", new StatsCollector.Statistic(9, 2.25, 4.0, 0.0)
        );

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        StatsCollector collector = new StatsCollector(3);
        metrics.forEach(metric -> {
            try {
                collector.push(metric.name(), metric.array());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread.sleep(1);
        collector.stop();
        executorService.shutdown();
        assertThat(collector.getResults()).containsExactlyInAnyOrderEntriesOf(expected);
    }
}
