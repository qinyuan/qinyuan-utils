package com.qinyuan15.utils.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Thread that is can be suspended
 * Created by qinyuan on 15-5-15.
 */
public abstract class SuspendableThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(SuspendableThread.class);
    private final static int DEFAULT_INTERVAL = 10;
    private boolean running = false;

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public final void run() {
        while (true) {
            if (this.running) {
                ThreadUtils.sleep(DEFAULT_INTERVAL);
            } else {
                try {
                    runJob();
                } catch (Throwable e) {
                    LOGGER.error("error in running SuspendableThread, info: {}", e);
                }
            }
        }
    }

    abstract protected void runJob();
}
