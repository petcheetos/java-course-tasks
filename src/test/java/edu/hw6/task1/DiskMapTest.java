package edu.hw6.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiskMapTest {

    @Test
    void testDiskMap() throws IOException {
        File file = new File("src/main/java/edu/hw6/task1/diskmap.txt");
        DiskMap diskMap = new DiskMap(file.toPath());

        assertTrue(diskMap.isEmpty());

        diskMap.put("firstKey", "firstValue");

        assertEquals(diskMap.size(), 1);
        assertEquals(diskMap.get("firstKey"), "firstValue");
        assertTrue(diskMap.containsKey("firstKey"));
        assertTrue(diskMap.containsValue("firstValue"));
        assertFalse(diskMap.containsKey("secondKey"));
        diskMap.saveToFile();

        String content = Files.readString(file.toPath());
        assertEquals("firstKey:firstValue", content.trim());

        diskMap.clear();
        diskMap.saveToFile();

        diskMap.put("secondKey", "secondValue");
        diskMap.saveToFile();
        diskMap.remove("secondKey");
        diskMap.saveToFile();
        assertFalse(diskMap.containsKey("secondKey"));

        assertTrue(diskMap.isEmpty());
    }

    @Test
    void testDiskMapException() {
        assertThrows(
            IllegalArgumentException.class,
            () -> new DiskMap(Path.of("src/main/java/edu/hw6/task1/text.txt"))
        );
    }
}
