package com.qinyuan15.utils.hibernate;

/**
 * Persist Object Of Hibernate
 * Created by qinyuan on 14-12-27.
 */
public abstract class PersistObject implements Persist {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
