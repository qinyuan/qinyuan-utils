package com.qinyuan15.utils.http;

import com.qinyuan15.utils.database.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.database.hibernate.HibernateUtils;
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

    public Proxy getInstance(int id) {
        return HibernateUtils.get(Proxy.class, id);
    }

    public List<Proxy> getInstances() {
        return new HibernateListBuilder().addOrder("speed", true).addOrder("speedUpdateTime", true)
                .build(Proxy.class);
    }

    public List<Proxy> getInstances(int size) {
        return new HibernateListBuilder().addOrder("speed", true).addOrder("speedUpdateTime", true)
                .limit(0, size).build(Proxy.class);
    }

    public List<Proxy> getSlowInstances(int size) {
        return new HibernateListBuilder().addFilter("speed=(SELECT MAX(speed) FROM Proxy)")
                .addOrder("speedUpdateTime", true).limit(0, size).build(Proxy.class);
    }

    public List<Proxy> getSlowInstances() {
        return new HibernateListBuilder().addFilter("speed=(SELECT MAX(speed) FROM Proxy)")
                .addOrder("rand()", true).build(Proxy.class);
    }

    public int getCount() {
        return new HibernateListBuilder().count(Proxy.class);
    }

    public int getSlowCount() {
        return new HibernateListBuilder().addFilter("speed=(SELECT MAX(speed) FROM Proxy)").count(Proxy.class);
    }

    public int getFastCount() {
        return new HibernateListBuilder().addFilter("speed<>(SELECT MAX(speed) FROM Proxy)").count(Proxy.class);
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
