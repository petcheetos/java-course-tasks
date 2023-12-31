package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class FileFilters {
    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;

    private FileFilters() {
    }

    public static AbstractFilter largerThan(Long size) {
        return file -> Files.size(file) > size;
    }

    public static AbstractFilter magicNumber(int... numbers) {
        return file -> {
            try {
                byte[] bytes = new byte[numbers.length];
                try (var inputStream = Files.newInputStream(file.toAbsolutePath())) {
                    int bytesRead = inputStream.read(bytes);
                    if (bytesRead < numbers.length) {
                        return false;
                    }
                }
                for (int i = 0; i < numbers.length; i++) {
                    if (bytes[i] != (byte) numbers[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                return false;
            }
        };
    }

    public static AbstractFilter globMatches(String extension) {
        return file -> {
            String fileName = file.getFileName().toString();
            int dotIndex = fileName.lastIndexOf('.');
            String fileExtension = fileName.substring(dotIndex);
            return fileExtension.equals(extension);
        };
    }

    public static AbstractFilter regexContains(String regex) {
        return file -> {
            String fileName = file.getFileName().toString();
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(fileName).find();
        };
    }
}
