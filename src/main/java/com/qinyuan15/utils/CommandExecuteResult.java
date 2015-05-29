package com.qinyuan15.utils;

/**
 * Class to wrap command execute result
 * Created by qinyuan on 15-5-29.
 */
public class CommandExecuteResult {
    private final int exitCode;
    private final String systemOut;
    private final String systemErr;

    public CommandExecuteResult(int exitCode, String systemOut, String systemErr) {
        this.exitCode = exitCode;
        this.systemOut = systemOut;
        this.systemErr = systemErr;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getSystemOut() {
        return systemOut;
    }

    public String getSystemErr() {
        return systemErr;
    }
}
