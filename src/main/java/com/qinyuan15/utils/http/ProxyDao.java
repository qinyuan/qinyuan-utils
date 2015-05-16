package com.qinyuan15.utils.http;

import com.qinyuan15.utils.hibernate.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Data access object of Proxy
 * Created by qinyuan on 15-1-1.
 */
public class ProxyDao {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProxyDao.class);

    public List<Proxy> getInstances() {
        return HibernateUtils.getList(Proxy.class, "ORDER BY speed asc, id desc");
    }

    public List<Proxy> getInstances(int size) {
        return HibernateUtils.getList(Proxy.class, "ORDER BY speed ASC,rand()", 0, size);
    }

    public List<Proxy> getSlowInstances(int size) {
        return HibernateUtils.getList(Proxy.class,
                "speed=(SELECT MAX(speed) FROM Proxy) ORDER BY rand()", 0, size);
    }

    public List<Proxy> getSlowInstances() {
        return HibernateUtils.getList(Proxy.class,
                "speed=(SELECT MAX(speed) FROM Proxy) ORDER BY rand()");
    }

    public int getCount() {
        return (int) HibernateUtils.getCount(Proxy.class);
    }

    public int getSlowCount() {
        return (int) HibernateUtils.getCount(Proxy.class, "speed=(SELECT MAX(speed) FROM Proxy)");
    }

    public void save(List<Proxy> proxies) {
        Session session = HibernateUtils.getSession();
        try {
            Query query = session.createQuery("FROM Proxy WHERE host=:host and port=:port");
            for (Proxy proxy : proxies) {
                if (query.setString("host", proxy.getHost()).setInteger("port", proxy.getPort())
                        .list().size() == 0) {
                    session.save(proxy);
                    LOGGER.info("save proxy {}.", proxy);
                } else {
                    LOGGER.info("{} already exists, no need to save.", proxy);
                }
            }
        } catch (Exception e) {
            LOGGER.error("fail to save: {}", e);
            throw new RuntimeException(e);
        } finally {
            HibernateUtils.commit(session);
        }
    }
}
