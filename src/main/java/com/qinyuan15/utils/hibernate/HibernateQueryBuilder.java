package com.qinyuan15.utils.hibernate;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to build query of hibernate
 * Created by qinyuan on 15-6-27.
 */
class HibernateQueryBuilder {

    private final Map<String, Object> arguments = new HashMap<>();

    private int firstResult;
    private int maxResults;

    public HibernateQueryBuilder limit(int firstResult, int maxResults) {
        this.firstResult = firstResult;
        this.maxResults = maxResults;
        return this;
    }

    public Query buildQuery(Session session, String hql) {
        Query query = session.createQuery(hql);
        setParametersOfQuery(query);
        return query;
    }

    public Query buildSQLQuery(Session session, String sql) {
        SQLQuery query = session.createSQLQuery(sql);
        setParametersOfQuery(query);
        return query;
    }

    public HibernateQueryBuilder addArgument(String key, Object value) {
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


}
