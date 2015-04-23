package com.qinyuan15.utils.http;

/**
 * Wrap the Http Response
 * Created by qinyuan on 14-12-24.
 */
public class HttpResponse {
    private String content;
    private int status;

    public HttpResponse(String content, int status) {
        this.content = content;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public int getStatus() {
        return status;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
