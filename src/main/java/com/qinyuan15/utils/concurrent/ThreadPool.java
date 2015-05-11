package com.qinyuan15.utils.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Thread pool
 * Created by qinyuan on 15-5-11.
 */
public class ThreadPool {
    private final static Logger LOGGER = LoggerFactory.getLogger(ThreadPool.class);

    private ExecutorService service;

    public ThreadPool(int size) {
        service = Executors.newFixedThreadPool(size);
    }

    /**
     * Add a thread and execute it
     *
     * @param thread thread to add
     * @return this
     */
    public ThreadPool add(Runnable thread) {
        service.execute(thread);
        return this;
    }

    /**
     * wait threads to complete
     *
     * @param maxSeconds max seconds to wait
     */
    public void waitToComplete(int maxSeconds) {
        service.shutdown();
        try {
            service.awaitTermination(maxSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LOGGER.error("Thread is Interrupted: {}", e);
        }
    }

    public void waitToComplete() {
        this.waitToComplete(Integer.MAX_VALUE);
    }
}
