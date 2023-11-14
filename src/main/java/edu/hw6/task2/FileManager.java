package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private FileManager() {
    }

    public static void cloneFile(Path path) throws IOException {
        if (path == null) {
            throw new NullPointerException("Path is null");
        }

        String originFileToStr = path.getFileName().toString();
        int dotIndex = originFileToStr.lastIndexOf('.');
        String originFileName = (dotIndex == -1) ? originFileToStr : originFileToStr.substring(0, dotIndex);
        String originFileExtension = (dotIndex == -1) ? "" : originFileToStr.substring(dotIndex);

        Path fileDirectory = path.getParent();

        int copyIndex = 1;
        Path copiedFile;
        do {
            String copyName = originFileName + " — копия" + (copyIndex > 1 ? " (" + copyIndex + ")" : "")
                + originFileExtension;
            copiedFile = Paths.get(fileDirectory.toString(), copyName);
            copyIndex++;
        } while (Files.exists(copiedFile));
        Files.copy(path, copiedFile);

    }
}
