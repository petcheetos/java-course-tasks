package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectorySearch extends RecursiveTask<List<File>> {
    private final Logger logger = LogManager.getLogger();
    private final File directory;

    public DirectorySearch(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<DirectorySearch> subTasks = new ArrayList<>();
        List<File> result = new ArrayList<>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    DirectorySearch subTask = new DirectorySearch(file);
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
        if (result.size() > 1000) {
            logger.info("Directory with more than 1000 files: " + directory.getAbsolutePath());
        }
        return result;
    }

}

