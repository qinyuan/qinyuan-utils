package com.qinyuan15.utils.security;

import com.qinyuan15.utils.hibernate.HibernateListBuilder;

/**
 * A simple dao to handle user
 * Created by qinyuan on 15-6-14.
 */
public class SimpleUserDao implements IUserDao {
    @Override
    public User getInstanceByName(String username) {
        return new HibernateListBuilder()
                .addFilter("username=:username").addArgument("username", username)
                .getFirstItem(User.class);
    }

    @Override
    public Integer getIdByName(String username) {
        User user = this.getInstanceByName(username);
        return user == null ? null : user.getId();
    }
}
