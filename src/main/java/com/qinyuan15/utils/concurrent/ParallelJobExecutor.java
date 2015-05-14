package com.qinyuan15.utils.concurrent;

/**
 * Class to execute parallel jobs,
 * if each of the jobs success, the job is completed
 * Created by qinyuan on 15-5-14.
 */
public class ParallelJobExecutor {
    private final int timeout;
    private ParallelThread successThread = null;
    private int threadSize = 0;

    /**
     * constructor
     *
     * @param timeoutMilliSeconds the longest milliseconds to complete job
     */
    public ParallelJobExecutor(int timeoutMilliSeconds) {
        this.timeout = timeoutMilliSeconds;
    }

    public void addJob(ParallelThread parallelThread) {
        if (this.successThread != null) {
            return;
        }
        parallelThread.start();
        parallelThread.executor = this;
        changeThreadSize(1);
    }

    public ParallelThread getSuccessThread() {
        long start = System.currentTimeMillis();
        while (true) {
            if (this.successThread != null) {
                return this.successThread;
            }

            if (threadSize <= 0 || System.currentTimeMillis() - start > timeout) {
                return null;
            }
            ThreadUtils.sleep(0.1);
        }
    }

    private synchronized void setSuccessThread(ParallelThread parallelThread) {
        if (this.successThread == null) {
            this.successThread = parallelThread;
        }
    }

    private synchronized void changeThreadSize(int n) {
        threadSize = threadSize + n;
    }

    public abstract static class ParallelThread extends Thread {
        private ParallelJobExecutor executor;

        protected abstract boolean runJob();

        protected boolean isSuccess() {
            return executor.successThread != null;
        }

        @Override
        public final void run() {
            if (runJob()) {
                executor.setSuccessThread(this);
            }
            executor.changeThreadSize(-1);
        }
    }
}
