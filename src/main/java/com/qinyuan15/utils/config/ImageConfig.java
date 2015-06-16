package com.qinyuan15.utils.config;

/**
 * Configuration about image
 * Created by qinyuan on 15-6-16.
 */
public class ImageConfig {
    private String directory;
    private String protocal;
    private int port;
    private String context;

    public String getDirectory() {
        return directory;
    }

    public String getProtocal() {
        return protocal;
    }

    public int getPort() {
        return port;
    }

    public String getContext() {
        return context;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setProtocal(String protocal) {
        this.protocal = protocal;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
