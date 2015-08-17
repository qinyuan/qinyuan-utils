package com.qinyuan15.utils.security;

import com.qinyuan15.utils.DateUtils;
import com.qinyuan15.utils.IntegerUtils;
import com.qinyuan15.utils.database.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.database.hibernate.HibernateUtils;
import com.qinyuan15.utils.ip.DefaultIpLocationQuerier;

import java.util.List;

public class LoginRecordDao {


    public List<LoginRecord> getInstances() {
        return new HibernateListBuilder().build(LoginRecord.class);
    }

    public static class Factory {
        private Integer userId;
        private boolean ascOrder = false;
        private int limitSize;

        public Factory setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Factory setAscOrder(boolean ascOrder) {
            this.ascOrder = ascOrder;
            return this;
        }

        public Factory setLimitSize(int limitSize) {
            this.limitSize = limitSize;
            return this;
        }

        public List<LoginRecord> getInstances() {
            HibernateListBuilder listBuilder = new HibernateListBuilder().addOrder("loginTime", ascOrder);
            if (IntegerUtils.isPositive(userId)) {
                listBuilder.addEqualFilter("userId", userId);
            }

            if (limitSize > 0) {
                listBuilder.limit(0, limitSize);
            }

            return listBuilder.build(LoginRecord.class);
        }
    }

    public static Factory factory() {
        return new Factory();
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
