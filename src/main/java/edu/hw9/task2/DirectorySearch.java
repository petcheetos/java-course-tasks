package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearch extends RecursiveTask<List<File>> {
    private final File directory;
    private final int numberOfFiles;

    public DirectorySearch(File directory, int numberOfFiles) {
        this.directory = directory;
        this.numberOfFiles = numberOfFiles;
    }

    @Override
    protected List<File> compute() {
        List<DirectorySearch> subTasks = new ArrayList<>();
        List<File> result = new ArrayList<>();
        File[] files = directory.listFiles();
        List<File> answer = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectorySearch subTask = new DirectorySearch(file, numberOfFiles);
                    subTasks.add(subTask);
                    subTask.fork();
                } else {
                    result.add(file);
                }
            }
        }
        for (DirectorySearch subTask : subTasks) {
            result.addAll(subTask.join());
        }
        if (result.size() > numberOfFiles) {
            answer.add(directory);
        }
        return result;
    }
}
