package com.qinyuan15.utils.hibernate;

import com.qinyuan15.utils.reflect.GenericUtils;

import java.util.List;

public class AbstractDao<T> {
    protected Class getPersistClass() {
        return GenericUtils.getRealTypeOfGenericArgument(this.getClass());
    }

    @SuppressWarnings("unchecked")
    public List<T> getInstances() {
        return new HibernateListBuilder().build(getPersistClass());
    }

    @SuppressWarnings("unchecked")
    public T getInstance(Integer id) {
        return (T) HibernateUtils.get(getPersistClass(), id);
    }

    public void delete(Integer id) {
        HibernateDeleter.deleteById(getPersistClass(), id);
    }
}
