package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HackerNewsTest {

    @Test
    void testHackerNewsTopStories() {
        assertTrue(HackerNews.hackerNewsTopStories().length != 0);
        assertEquals("JDK 21 Release Notes", HackerNews.news(37570037));
    }
}
