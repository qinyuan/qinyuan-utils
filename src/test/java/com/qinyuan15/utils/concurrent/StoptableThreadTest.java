package com.qinyuan15.utils.concurrent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test StoptableThread
 * Created by qinyuan on 15-5-24.
 */
public class StoptableThreadTest {
    @Test
    public void testRun() throws Exception {
        StoptableThread thread = new TestThread();
        thread.start();
        ThreadUtils.sleep(5);
        System.out.println("before stop");
        thread.stopSafely();
        System.out.println("after stop");
        ThreadUtils.sleep(5);
    }

    @Test
    public void testIsBlocked() {
        StoptableThread thread = new TestThread2();
        thread.setMaxHeartbeatInterval(2);

        assertThat(thread.isBlocked()).isFalse();

        thread.start();

        assertThat(thread.isBlocked()).isFalse();

        ThreadUtils.sleep(1);
        assertThat(thread.isBlocked()).isFalse();

        ThreadUtils.sleep(1.5);

        assertThat(thread.isBlocked()).isTrue();
    }

    private class TestThread extends StoptableThread {
        @Override
        protected void jobToRun() {
            System.out.println("jobToRun");
            ThreadUtils.sleep(1);
        }
    }

    private class TestThread2 extends StoptableThread {
        @Override
        protected void jobToRun() {
            ThreadUtils.sleep(10);
        }
    }
}
