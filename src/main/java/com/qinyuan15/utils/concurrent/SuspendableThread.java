package com.qinyuan15.utils.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread that is can be suspended
 * Created by qinyuan on 15-5-15.
 */
public abstract class SuspendableThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(SuspendableThread.class);
    public final static int DEFAULT_INTERVAL = 10;

    private int updateInterval = DEFAULT_INTERVAL;
    private boolean running = false;

    protected final void setUpdateInterval(int updateInterval) {
        this.updateInterval = updateInterval;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public final void run() {
        while (true) {
            if (this.running) {
                try {
                    runJob();
                } catch (Throwable e) {
                    LOGGER.error("error in running SuspendableThread, info: {}", e);
                }
            } else {
                ThreadUtils.sleep(updateInterval);
            }
        }
    }

    abstract protected void runJob();
}
