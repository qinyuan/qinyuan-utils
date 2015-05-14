package com.qinyuan15.utils.concurrent;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ParallelJobExecutor
 * Created by qinyuan on 15-5-15.
 */
public class ParallelJobExecutorTest {
    @Test
    public void testGetSuccessThread() {
        ParallelJobExecutor executor = new ParallelJobExecutor(50000);
        executor.addJob(new SuccessThread(15));
        executor.addJob(new SuccessThread(8));
        executor.addJob(new SuccessThread(17));
        executor.addJob(new SuccessThread(20));
        executor.addJob(new SuccessThread(3));
        int interval = ((SuccessThread) executor.getSuccessThread()).interval;
        assertThat(interval).isEqualTo(3);
    }

    @Test
    public void testGetSuccessThread2() {
        ParallelJobExecutor executor = new ParallelJobExecutor(3000);
        executor.addJob(new SuccessThread(15));
        executor.addJob(new SuccessThread(8));
        executor.addJob(new SuccessThread(17));
        executor.addJob(new SuccessThread(20));
        assertThat(executor.getSuccessThread()).isNull();
    }

    @Test
    public void testGetSuccessThread3() {
        long start = System.currentTimeMillis();
        ParallelJobExecutor executor = new ParallelJobExecutor(30000);
        executor.addJob(new FailThread(2));
        executor.addJob(new FailThread(2));
        executor.addJob(new FailThread(2));
        executor.addJob(new FailThread(2));
        assertThat(executor.getSuccessThread()).isNull();
        assertThat(System.currentTimeMillis() - start).isLessThan(3000);
    }

    @Test
    public void testGetSuccessThread4() {
        ParallelJobExecutor executor = new ParallelJobExecutor(30000);
        executor.addJob(new FailThread(2));
        executor.addJob(new SuccessThread(3));
        int interval = ((SuccessThread) executor.getSuccessThread()).interval;
        assertThat(interval).isEqualTo(3);
    }

    private static class SuccessThread extends ParallelJobExecutor.ParallelThread {
        final int interval;

        public SuccessThread(int interval) {
            this.interval = interval;
        }

        @Override
        protected boolean runJob() {
            ThreadUtils.sleep(interval);
            return true;
        }
    }

    private static class FailThread extends ParallelJobExecutor.ParallelThread {
        final int interval;

        public FailThread(int interval) {
            this.interval = interval;
        }

        @Override
        protected boolean runJob() {
            ThreadUtils.sleep(interval);
            return false;
        }
    }
}
