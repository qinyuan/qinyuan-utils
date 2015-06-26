package com.qinyuan15.utils.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to build Hibernate list
 * Created by qinyuan on 15-5-26.
 */
public class HibernateListBuilder {
    private final static Logger LOGGER = LoggerFactory.getLogger(HibernateListBuilder.class);

    private final SQLConditionBuilder conditionBuilder = new SQLConditionBuilder();
    private final Map<String, Object> arguments = new HashMap<>();
    private int firstResult;
    private int maxResults;

    public HibernateListBuilder addFilter(String filter) {
        conditionBuilder.addFilter(filter);
        return this;
    }

    public HibernateListBuilder addOrder(String field, boolean asc) {
        conditionBuilder.addOrder(field, asc);
        return this;
    }

    public HibernateListBuilder addGroup(String field) {
        conditionBuilder.addGroup(field);
        return this;
    }

    public HibernateListBuilder limit(int firstResult, int maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        return this;
    }

    public HibernateListBuilder addArgument(String key, Object value) {
        arguments.put(key, value);
        return this;
    }

    private void setParametersOfQuery(Query query) {
        if (firstResult >= 0 && maxResults > 0) {
            query.setFirstResult(firstResult).setMaxResults(maxResults);
        }

        for (Map.Entry<String, Object> entry : arguments.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Integer) {
                query.setInteger(key, (Integer) value);
            } else if (value instanceof Double) {
                query.setDouble(key, (Double) value);
            } else if (value instanceof Boolean) {
                query.setBoolean(key, (Boolean) value);
            } else {
                query.setString(key, value.toString());
            }
        }
    }

    private Query buildQuery(Session session, String hql) {
        Query query = session.createQuery(hql);
        setParametersOfQuery(query);
        return query;
    }

    private Query buildSQLQuery(Session session, String sql) {
        SQLQuery query = session.createSQLQuery(sql);
        setParametersOfQuery(query);
        return query;
    }

    public int count(Class<?> clazz) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "SELECT COUNT(*) FROM " + clazz.getSimpleName() + conditionBuilder.build();
            @SuppressWarnings("unchecked")
            List<Long> list = buildQuery(session, hql).list();
            return (int) ((long) list.get(0));
        } catch (Throwable e) {
            LOGGER.error("fail to get count: {}", e);
            throw e;
        } finally {
            session.close();   // ensure session is closed
        }
    }

    public <T> List<T> build(Class<T> clazz) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "FROM " + clazz.getSimpleName() + conditionBuilder.build();
            @SuppressWarnings("unchecked")
            List<T> list = buildQuery(session, hql).list();
            return list;
        } catch (Throwable e) {
            LOGGER.error("fail to get list: {}", e);
            throw e;
        } finally {
            session.close();   // ensure session is closed
        }
    }

    /**
     * build list by raw SQL
     *
     * @return list build by raw SQL
     */
    public List<Object[]> buildBySQL(String sql) {
        Session session = HibernateUtils.getSession();
        try {
            sql = sql + conditionBuilder.build();
            @SuppressWarnings("unchecked")
            List<Object[]> list = buildSQLQuery(session, sql).list();
            return list;
        } catch (Throwable e) {
            LOGGER.error("fail to get list: {}", e);
            throw e;
        } finally {
            session.close();   // ensure session is closed
        }
    }

    public <T> T getFirstItem(Class<T> clazz) {
        List<T> items = build(clazz);
        return items.size() == 0 ? null : items.get(0);
    }
}
