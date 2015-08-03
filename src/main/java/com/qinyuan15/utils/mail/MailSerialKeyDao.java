package com.qinyuan15.utils.mail;

import com.qinyuan15.utils.DateUtils;
import com.qinyuan15.utils.IntegerUtils;
import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import java.sql.Date;

public abstract class MailSerialKeyDao {
    private final static int SERIAL_KEY_LENGTH = 100;

    abstract protected String getMailType();

    abstract protected int getExpireSeconds();

    public Integer add(Integer userId) {
        return add(userId, "");
    }

    public Integer add(Integer userId, String serialKeyPrefix) {
        MailSerialKey mailSerialKey = new MailSerialKey();
        mailSerialKey.setUserId(userId);

        do {
            mailSerialKey.setSerialKey(serialKeyPrefix + RandomStringUtils.randomAlphanumeric(SERIAL_KEY_LENGTH));
        } while (getInstanceBySerialKey(mailSerialKey.getSerialKey()) != null);

        mailSerialKey.setSendTime(DateUtils.nowString());
        mailSerialKey.setMailType(getMailType());
        return HibernateUtils.save(mailSerialKey);
    }

    public MailSerialKey getInstance(Integer id) {
        MailSerialKey mailSerialKey = HibernateUtils.get(MailSerialKey.class, id);
        return filterByMailTypeAndExpireSeconds(mailSerialKey);
    }

    public MailSerialKey getInstanceByUserId(Integer userId) {
        MailSerialKey mailSerialKey = new HibernateListBuilder().addFilter("userId=:userId").addOrder("id", true)
                .addArgument("userId", userId).getFirstItem(MailSerialKey.class);
        return filterByMailTypeAndExpireSeconds(mailSerialKey);
    }

    public MailSerialKey getInstanceBySerialKey(String serialKey) {
        MailSerialKey mailSerialKey = new HibernateListBuilder().addEqualFilter("serialKey", serialKey)
                .getFirstItem(MailSerialKey.class);
        return filterByMailTypeAndExpireSeconds(mailSerialKey);
    }

    public boolean hasSerialKey(String serialKey) {
        return new HibernateListBuilder().addEqualFilter("serialKey", serialKey)
                .addEqualFilter("mailType", getMailType()).count(MailSerialKey.class) > 0;
    }

    public void response(Integer id) {
        if (!IntegerUtils.isPositive(id)) {
            return;
        }

        String hql = "UPDATE MailSerialKey SET responseTime='" + DateUtils.nowString() + "' WHERE id=" + id;
        HibernateUtils.executeUpdate(hql);
    }

    private MailSerialKey filterByMailTypeAndExpireSeconds(MailSerialKey mailSerialKey) {
        // validate null
        if (mailSerialKey == null) {
            return null;
        }

        // validate mailType
        if (getMailType() == null || mailSerialKey.getMailType() == null
                || (!getMailType().equals(mailSerialKey.getMailType()))) {
            return null;
        }

        // validate send time
        if (!StringUtils.hasText(mailSerialKey.getSendTime())) {
            return null;
        }
        Date sendTime = DateUtils.newDate(DateUtils.trimMilliSecond(mailSerialKey.getSendTime()));
        if (DateUtils.getSecondDiff(sendTime, DateUtils.now()) > getExpireSeconds()) {
            return null;
        }

        return mailSerialKey;
    }
}
