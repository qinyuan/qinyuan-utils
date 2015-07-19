package com.qinyuan15.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class to query security information
 * Created by qinyuan on 15-3-6.
 */
public class SecuritySearcher {
    private final static Logger LOGGER = LoggerFactory.getLogger(SecuritySearcher.class);
    public final static String ADMIN = "ROLE_ADMIN";
    public final static String SUPPER_ADMIN = "ROLE_SUPPER_ADMIN";

    private final IUserDao userDao;

    public SecuritySearcher(IUserDao userDao) {
        this.userDao = userDao;
    }

    public Integer getUserId() {
        return this.userDao.getIdByName(SecurityUtils.getUsername());
    }

    public boolean isAdmin() {
        return SecurityUtils.hasAuthority(ADMIN);
    }

    public boolean isSupperAdmin() {
        return SecurityUtils.hasAuthority(SUPPER_ADMIN);
    }
}
