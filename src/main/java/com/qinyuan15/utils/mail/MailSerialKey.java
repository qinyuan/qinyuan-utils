package com.qinyuan15.utils.mail;

import com.qinyuan15.utils.database.hibernate.PersistObject;import java.lang.Integer;import java.lang.String;

/**
 * Class to record information about mail request with serial key.
 * <p>
 * Activate request is sent when new user is registering
 * </p>
 * Created by qinyuan on 15-7-1.
 */
public class MailSerialKey extends PersistObject {
    private Integer userId;
    private String serialKey;
    private String sendTime;
    private String responseTime;
    private String mailType;

    public Integer getUserId() {
        return userId;
    }

    public String getSerialKey() {
        return serialKey;
    }

    public String getSendTime() {
        return sendTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public String getMailType() {
        return mailType;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setSerialKey(String serialKey) {
        this.serialKey = serialKey;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }
}
