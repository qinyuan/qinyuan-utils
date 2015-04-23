package com.qinyuan15.utils.security;

/**
 * Interface of user dao
 * Created by qinyuan on 15-4-22.
 */
public interface IUserDao {
    IUser getInstanceByName(String username);
    Integer getIdByName(String username);
}
