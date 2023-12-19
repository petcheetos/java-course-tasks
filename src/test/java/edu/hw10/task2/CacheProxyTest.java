package edu.hw10.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;

public class CacheProxyTest {

    @Test
    void test(@TempDir Path tempDir) throws IOException {
        Path path = tempDir.resolve("cache.txt");
        Fibonacci fibonacci = new Fibonacci();
        FibCalculator fibCalculator = CacheProxy.create(fibonacci, FibCalculator.class, path);
        fibCalculator.fib(5);
        assertThat(Files.readString(path)).contains("5");
        fibCalculator.fib(8);
        assertThat(Files.readString(path)).contains("21");
    }
}
