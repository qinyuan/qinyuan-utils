package com.qinyuan15.utils.security;

import com.qinyuan15.utils.DateUtils;
import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;
import com.qinyuan15.utils.ip.DefaultIpLocationQuerier;

import java.util.List;

public class LoginRecordDao {
    public List<LoginRecord> getInstances() {
        return new HibernateListBuilder().build(LoginRecord.class);
    }

    private Integer add(Integer userId, String ip, String location, String loginTime) {
        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUserId(userId);
        loginRecord.setLoginTime(loginTime);
        loginRecord.setIp(ip);
        loginRecord.setLocation(location);
        return HibernateUtils.save(loginRecord);
    }

    public Integer add(Integer userId, String ip) {
        return add(userId, ip, new DefaultIpLocationQuerier().getLocation(ip), DateUtils.nowString());
    }
}
