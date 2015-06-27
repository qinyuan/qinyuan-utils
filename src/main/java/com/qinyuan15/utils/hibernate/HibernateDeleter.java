package com.qinyuan15.utils.hibernate;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class to build Hibernate list
 * Created by qinyuan on 15-5-26.
 */
public class HibernateDeleter {
    private final static Logger LOGGER = LoggerFactory.getLogger(HibernateDeleter.class);

    private final SQLConditionBuilder conditionBuilder = new SQLConditionBuilder();
    private final HibernateQueryBuilder queryBuilder = new HibernateQueryBuilder();

    /**
     * add filter condition
     *
     * @param filter filter clause such as "hello=:hello"
     * @return Object itself
     */
    public HibernateDeleter addFilter(String filter) {
        conditionBuilder.addFilter(filter);
        return this;
    }

    public HibernateDeleter addArgument(String key, Object value) {
        this.queryBuilder.addArgument(key, value);
        return this;
    }

    public void delete(Class<?> clazz) {
        Session session = HibernateUtils.getSession();
        try {
            String hql = "DELETE FROM " + clazz.getSimpleName() + conditionBuilder.build();
            this.queryBuilder.buildQuery(session, hql).executeUpdate();
        } catch (Throwable e) {
            LOGGER.error("fail to delete {}, info: {}", clazz.getSimpleName(), e);
            throw e;
        } finally {
            session.close();   // ensure session is closed
        }
    }

    /**
     * delete data by raw SQL
     */
    public void deleteBySQL(String sql) {
        Session session = HibernateUtils.getSession();
        try {
            sql = sql + conditionBuilder.build();
            this.queryBuilder.buildSQLQuery(session, sql).executeUpdate();
        } catch (Throwable e) {
            LOGGER.error("fail to get list: {}", e);
            throw e;
        } finally {
            session.close();   // ensure session is closed
        }
    }

    public static void deleteById(Class<?> clazz, Integer id) {
        new HibernateDeleter().addFilter("id=:id").addArgument("id", id).delete(clazz);
    }

    public static void deleteAll(Class<?> clazz) {
        new HibernateDeleter().delete(clazz);
    }
}
