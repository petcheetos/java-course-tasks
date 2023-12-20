package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DirectorySearchTest {

    @Test
    void testDirectorySearch() {
        File file = new File("src/main/java/edu/hw9/task2");
        List<File> result;
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectorySearch search = new DirectorySearch(file, 2);
            result = forkJoinPool.invoke(search);
        }
        assertThat(result.size()).isEqualTo(2);
    }
}
