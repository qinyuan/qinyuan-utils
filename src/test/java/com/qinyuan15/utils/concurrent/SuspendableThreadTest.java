package com.qinyuan15.utils.concurrent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test SuspendableThread
 * Created by qinyuan on 15-5-16.
 */
public class SuspendableThreadTest {

    private boolean running = false;

    @Test
    public void testRunJob() throws Exception {
        TestThread testThread = new TestThread();
        testThread.start();

        ThreadUtils.sleep(2);
        assertThat(running).isFalse();
        testThread.setRunning(true);
        ThreadUtils.sleep(2);
        assertThat(running).isTrue();
    }

    private class TestThread extends SuspendableThread {
        TestThread() {
            setUpdateInterval(1);
        }

        @Override
        protected void runJob() {
            running = true;
        }
    }
}
