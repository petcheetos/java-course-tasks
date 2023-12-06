package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class KeywordQuoteServer {
    private final ExecutorService executor;
    private final Semaphore semaphore;
    private final InetSocketAddress address;
    private final int maxConnections = 3;
    private ServerSocketChannel serverSocketChannel = null;

    public KeywordQuoteServer(String host, int port, int numberOfThreads) {
        executor = Executors.newFixedThreadPool(numberOfThreads);
        semaphore = new Semaphore(maxConnections, true);
        address = new InetSocketAddress(host, port);
    }

    public void start() {
        try (Selector selector = Selector.open()) {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(address);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (serverSocketChannel.isOpen()) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (semaphore.tryAcquire()) {
                        if (key.isAcceptable()) {
                            SocketChannel client = serverSocketChannel.accept();
                            startExecution(client);
                        }
                    }
                    selectedKeys.remove(key);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startExecution(SocketChannel client) {
        executor.execute(new KeywordQuoteWorker(client, semaphore));
    }

    public void stop() {
        executor.shutdownNow();
        semaphore.release(maxConnections);
        try {
            serverSocketChannel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
