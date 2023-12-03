package edu.hw8.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class KeywordQuoteWorker implements Runnable {

    private final int bufferSize = 1024;
    private final Semaphore semaphore;
    private final SocketChannel socketChannel;
    private final String serverSaid = "Server: ";
    private final String clientSaid = "Client: ";
    private final Map<String, String> responses = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    public KeywordQuoteWorker(SocketChannel socketChannel, Semaphore semaphore) {
        this.socketChannel = socketChannel;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try (Selector selector = Selector.open()) {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (socketChannel.isOpen()) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (key.isReadable()) {
                        executeResponse();
                        socketChannel.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    private void executeResponse() {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        int bytesRead;
        try {
            bytesRead = socketChannel.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (bytesRead != -1) {
            String request = new String(buffer.array(), StandardCharsets.UTF_8);
            System.out.println(clientSaid + request);
            String responseMessage = responses.get(request.trim());
            ByteBuffer byteBuffer = ByteBuffer.wrap((serverSaid + responseMessage).getBytes(StandardCharsets.UTF_8));
            while (byteBuffer.hasRemaining()) {
                try {
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
