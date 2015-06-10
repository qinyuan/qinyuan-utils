package com.qinyuan15.utils.http;

import com.qinyuan15.utils.hibernate.HibernateListBuilder;

/**
 * Dao about ProxyRejection
 * Created by qinyuan on 15-6-4.
 */
public class ProxyRejectionDao {

    public boolean hasInstance(int proxyId, String host) {
        int count = new HibernateListBuilder()
                .addFilter("host=:host")
                .addFilter("proxyId=:proxyId")
                .addArgument("host", host)
                .addArgument("proxyId", proxyId)
                .count(ProxyRejection.class);
        return count > 0;
    }
}
