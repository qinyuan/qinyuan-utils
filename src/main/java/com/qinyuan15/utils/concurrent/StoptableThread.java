package com.qinyuan15.utils.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread that can be stopped
 * Created by qinyuan on 15-5-24.
 */
public abstract class StoptableThread extends Thread implements Stoptable {
    private final static Logger LOGGER = LoggerFactory.getLogger(StoptableThread.class);
    private boolean running = true;
    private int maxHeartbeatInterval;
    private long lastInvokedTimestamp;

    public StoptableThread setMaxHeartbeatInterval(int seconds) {
        this.maxHeartbeatInterval = seconds;
        return this;
    }

    @Override
    public void stopSafely() {
        this.running = false;
    }

    public boolean isBlocked() {
        if (maxHeartbeatInterval <= 0) {
            return false;
        }

        if (lastInvokedTimestamp == 0) {
            return false;
        }

        long lastInvokedToNow = System.currentTimeMillis() - lastInvokedTimestamp;
        return lastInvokedToNow > maxHeartbeatInterval * 1000;
    }

    @Override
    public final void run() {
        while (this.running) {
            try {
                this.lastInvokedTimestamp = System.currentTimeMillis();
                jobToRun();
            } catch (Throwable e) {
                LOGGER.error("error in running StoptableThread, info: {}", e);
            }
        }
    }

    abstract protected void jobToRun();
}
