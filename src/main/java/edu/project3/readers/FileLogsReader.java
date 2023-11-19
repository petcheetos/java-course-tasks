package edu.project3.readers;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogsReader implements LogsReader {

    @Override
    public String read(URI uri) {
        try {
            Path path = Paths.get(uri);
            byte[] encodedBytes = Files.readAllBytes(path);
            return new String(encodedBytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}
