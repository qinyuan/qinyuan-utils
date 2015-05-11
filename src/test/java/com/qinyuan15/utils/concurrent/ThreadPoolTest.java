package com.qinyuan15.utils.concurrent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ThreadPool
 * Created by qinyuan on 15-5-11.
 */
public class ThreadPoolTest {
    @Test
    public void testWaitToComplete() throws Exception {
        long start = System.currentTimeMillis();

        ThreadPool pool = new ThreadPool(3);
        pool.add(new MyThread(1));
        pool.add(new MyThread(2));
        pool.add(new MyThread(3));
        pool.add(new MyThread(4));
        pool.add(new MyThread(5));

        assertThat(threadCount).isEqualTo(5);
        pool.waitToComplete();
        assertThat(threadCount).isEqualTo(0);

        System.out.println("total time: " + (System.currentTimeMillis() - start) / 1000.0);
    }

    private int threadCount = 0;

    private synchronized void changeThreadCount(int n) {
        threadCount = threadCount + n;
    }

    private class MyThread extends Thread {
        private int id;

        public MyThread(int id) {
            this.id = id;
            changeThreadCount(1);
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread " + id + ":" + i);
                ThreadUtils.sleep(0.25);
            }
            changeThreadCount(-1);
        }
    }
}
