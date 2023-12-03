package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> blockingQueue;
    private final Worker[] workers;
    private final AtomicBoolean isWorking;

    public FixedThreadPool(int threads) {
        this.blockingQueue = new LinkedBlockingQueue<>();
        this.workers = new Worker[threads];
        this.isWorking = new AtomicBoolean(true);
        start();
    }

    @Override
    public void start() {
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isWorking.get()) {
            try {
                blockingQueue.put(runnable);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        isWorking.set(false);
    }

    public static FixedThreadPool create(int threads) {
        if (threads < 1) {
            throw new IllegalArgumentException();
        }
        return new FixedThreadPool(threads);
    }

    public class Worker extends Thread {

        @Override
        public void run() {
            while (isWorking.get() || !blockingQueue.isEmpty()) {
                Runnable runnable = blockingQueue.poll();
                while (runnable != null) {
                    runnable.run();
                    runnable = blockingQueue.poll();
                }
            }
        }
    }
}


