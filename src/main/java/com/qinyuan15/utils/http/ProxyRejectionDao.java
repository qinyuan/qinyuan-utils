package com.qinyuan15.utils.http;

import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;
import com.qinyuan15.utils.mvc.controller.PaginationItemFactory;

import java.util.List;

/**
 * Dao about ProxyRejection
 * Created by qinyuan on 15-6-4.
 */
public class ProxyRejectionDao {

    public ProxyRejection getInstance(Integer id) {
        return HibernateUtils.get(ProxyRejection.class, id);
    }

    public boolean hasInstance(int proxyId, String host) {
        int count = new HibernateListBuilder()
                .addFilter("host=:host")
                .addFilter("proxyId=:proxyId")
                .addArgument("host", host)
                .addArgument("proxyId", proxyId)
                .count(ProxyRejection.class);
        return count > 0;
    }

    public static Factory factory() {
        return new Factory();
    }

    public static class Factory implements PaginationItemFactory<ProxyRejection> {
        @Override
        public long getCount() {
            return HibernateUtils.getCount(ProxyRejection.class);
        }

        @Override
        public List<ProxyRejection> getInstances(int firstResult, int maxResults) {
            return HibernateUtils.getList(ProxyRejection.class, "ORDER BY rejectTime DESC, id DESC",
                    firstResult, maxResults);
        }
    }
}
