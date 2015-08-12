package com.qinyuan15.utils.hibernate;

/**
 * Persist Object Of Hibernate
 * Created by qinyuan on 14-12-27.
 */
public abstract class PersistObject implements Persist {
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
