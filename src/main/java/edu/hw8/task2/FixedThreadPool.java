package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
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
                if (!blockingQueue.offer(runnable, 1, TimeUnit.SECONDS)) {
                    throw new RejectedExecutionException("Task cannot be added to the queue");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void close() {
        isWorking.set(false);
        for (Worker worker : workers) {
            worker.interrupt();
        }
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
            try {
                while (isWorking.get() || !blockingQueue.isEmpty()) {
                    Runnable runnable = blockingQueue.take();
                    runnable.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}


