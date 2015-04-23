package com.qinyuan15.utils;

import org.junit.Test;

/**
 * Test ThreadUtils
 * Created by qinyuan on 15-4-19.
 */
public class ThreadUtilsTest {
    @Test
    public void testSleep() throws Exception {
        new TestThread(2).start();
        new TestThread(1).start();

        ThreadUtils.sleep(5);
    }

    private class TestThread extends Thread {
        private int interval;

        TestThread(int interval) {
            this.interval = interval;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println("Thread: " + interval);
                ThreadUtils.sleep(interval);
            }
        }
    }
}
