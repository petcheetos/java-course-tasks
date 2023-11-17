package edu.hw6.task6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PortScannerTest {
    @Test
    void testScanPorts() {
        assertFalse(PortScanner.scanPorts().isEmpty());
    }
}
