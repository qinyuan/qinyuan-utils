package com.qinyuan15.utils.mail;

import com.qinyuan15.utils.DateUtils;
import com.qinyuan15.utils.IntegerUtils;
import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;
import org.apache.commons.lang3.RandomStringUtils;import java.lang.Integer;import java.lang.String;

public abstract class MailSerialKeyDao {
    private final static int SERIAL_KEY_LENGTH = 100;

    abstract protected String getMailType();

    public Integer add(Integer userId) {
        MailSerialKey mailSerialKey = new MailSerialKey();
        mailSerialKey.setUserId(userId);

        do {
            mailSerialKey.setSerialKey(RandomStringUtils.randomAlphanumeric(SERIAL_KEY_LENGTH));
        } while (getInstanceBySerialKey(mailSerialKey.getSerialKey()) != null);

        mailSerialKey.setSendTime(DateUtils.nowString());
        mailSerialKey.setMailType(getMailType());
        return HibernateUtils.save(mailSerialKey);
    }

    public MailSerialKey getInstance(Integer id) {
        MailSerialKey mailSerialKey = HibernateUtils.get(MailSerialKey.class, id);
        return filterByMailType(mailSerialKey);
    }

    public MailSerialKey getInstanceByUserId(Integer userId) {
        MailSerialKey mailSerialKey = new HibernateListBuilder().addFilter("userId=:userId").addOrder("id", true)
                .addArgument("userId", userId).getFirstItem(MailSerialKey.class);
        return filterByMailType(mailSerialKey);
    }

    public MailSerialKey getInstanceBySerialKey(String serialKey) {
        MailSerialKey mailSerialKey = new HibernateListBuilder().addEqualFilter("serialKey", serialKey)
                .getFirstItem(MailSerialKey.class);
        return filterByMailType(mailSerialKey);
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

    private MailSerialKey filterByMailType(MailSerialKey mailSerialKey) {
        if (mailSerialKey == null || getMailType() == null || mailSerialKey.getMailType() == null) {
            return null;
        }
        return getMailType().equals(mailSerialKey.getMailType()) ? mailSerialKey : null;
    }
}
