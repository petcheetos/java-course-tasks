package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateSearch extends RecursiveTask<List<Path>> {
    private final Path directory;
    private final Predicate<Path> predicate;

    public PredicateSearch(Path directory, Predicate<Path> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<Path> compute() {
        List<Path> result = new ArrayList<>();
        List<PredicateSearch> predicateSearchList = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(directory, 1)) {
            walk.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    if (predicate.test(path)) {
                        result.add(path);
                    }
                } else if (Files.isDirectory(path) && !path.equals(directory)) {
                    PredicateSearch nextTask = new PredicateSearch(path, predicate);
                    nextTask.fork();
                    predicateSearchList.add(nextTask);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (PredicateSearch task : predicateSearchList) {
            result.addAll(task.join());
        }
        return result;
    }
}
