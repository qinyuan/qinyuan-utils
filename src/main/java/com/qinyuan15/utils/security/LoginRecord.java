package com.qinyuan15.utils.security;

import com.qinyuan15.utils.hibernate.PersistObject;

public class LoginRecord extends PersistObject {
    private Integer userId;
    private String loginTime;
    private String ip;
    private String location;

    public Integer getUserId() {
        return userId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public String getIp() {
        return ip;
    }

    public String getLocation() {
        return location;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
