package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    @Test
    @DisplayName("Testing game in session, incorrect symbol input, expected error game status")
    public void testWithIncorrectInputShouldReturnError() {
        String data = "ab\n";
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        Session session = new Session("kitten");
        session.run();
        assertEquals(GameStatus.Error, session.status);
    }
}
