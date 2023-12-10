package edu.hw9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PredicateSearchTest {

    @Test
    void testPredicateSearch() {
        File file = new File("src/test/java/edu/hw9");

        List<Path> result;
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            PredicateSearch search = new PredicateSearch(file.toPath(), p -> p.toString().endsWith(".txt"));
            result = forkJoinPool.invoke(search);
        }
        assertThat(result.size()).isEqualTo(0);
    }
}
