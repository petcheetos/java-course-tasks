package edu.hw8.task1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KeywordServerTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void testKeywordQuoteServer() throws IOException, InterruptedException {
        System.setOut(new PrintStream(outputStream));
        KeywordQuoteServer server = new KeywordQuoteServer("localhost", 8080, 3);
        KeywordQuoteClient client = new KeywordQuoteClient("localhost", 8080);
        Thread serverThread = new Thread(server::start);
        serverThread.start();
        Thread.sleep(100);
        client.sendMessage("интеллект");
        assertThat(outputStream.toString().replace("\u0000", "").trim()).isEqualTo("Client: интеллект\r\n"
            + "Server: Чем ниже интеллект, тем громче оскорбления");
    }
}
