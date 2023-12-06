package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class KeywordQuoteClient {
    private final InetSocketAddress address;
    private final int bufferSize = 1024;

    public KeywordQuoteClient(String host, int port) {
        address = new InetSocketAddress(host, port);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void sendMessage(String message) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open(address)) {
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
            buffer.flip();
            buffer = ByteBuffer.allocate(bufferSize);
            int bytesRead = socketChannel.read(buffer);
            if (bytesRead != -1) {
                System.out.println(new String(buffer.array(), StandardCharsets.UTF_8));
            }
        }
    }
}
