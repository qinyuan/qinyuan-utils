package com.qinyuan15.utils.mvc.controller;

import com.qinyuan15.utils.database.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.reflect.GenericUtils;

import java.util.List;

public abstract class AbstractPaginationItemFactory<T> implements PaginationItemFactory<T> {
    @Override
    public long getCount() {
        return getListBuilder().count(GenericUtils.getRealTypeOfGenericArgument(this.getClass()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getInstances(int firstResult, int maxResults) {
        return (List) getListBuilder().limit(firstResult, maxResults).build(
                GenericUtils.getRealTypeOfGenericArgument(this.getClass()));
    }

    protected HibernateListBuilder getListBuilder() {
        return new HibernateListBuilder();
    }
}
