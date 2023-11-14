package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final static Logger LOGGER = LogManager.getLogger();
    private final Map<String, String> inMemoryCache;
    private final Path filePath;
    private static final String SPLITTER = ":";
    private static final String NOT_EXIST = "File does not exist";
    private static final String UPDATED = "File was updated";
    private static final String READ = "Data was read";
    private static final String NOT_READ = "Data was not read";

    public DiskMap(Path path) {
        this.filePath = path;
        this.inMemoryCache = new HashMap<>();
        readFromFile();
    }

    @Override
    public int size() {
        return inMemoryCache.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryCache.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryCache.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryCache.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryCache.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return inMemoryCache.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return inMemoryCache.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        inMemoryCache.putAll(m);
    }

    @Override
    public void clear() {
        inMemoryCache.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return inMemoryCache.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return inMemoryCache.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryCache.entrySet();
    }

    public void saveToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Entry<String, String> entry : entrySet()) {
                writer.write(entry.getKey() + SPLITTER + entry.getValue());
                writer.newLine();
            }
            LOGGER.info(UPDATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException(NOT_EXIST);
        }
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(SPLITTER);
                if (strings.length == 2) {
                    inMemoryCache.put(strings[0], strings[1]);
                } else {
                    LOGGER.info(NOT_READ);
                    return;
                }
            }
            LOGGER.info(READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
