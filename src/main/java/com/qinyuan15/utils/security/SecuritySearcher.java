package com.qinyuan15.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

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
        return this.userDao.getIdByName(getUsername());
    }

    public String getUsername() {
        UserDetails userDetails = getUserDetails();
        return userDetails == null ? null : userDetails.getUsername();
    }

    public UserDetails getUserDetails() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            return (UserDetails) userDetails;
        } else {
            LOGGER.info("userDetails is String, whose value is {}", userDetails);
            return null;
        }
    }

    public List<String> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        UserDetails userDetails = getUserDetails();
        if (userDetails == null) {
            return authorities;
        }

        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return authorities;
    }

    public boolean hasAuthority(String roleName) {
        return getAuthorities().contains(roleName);
    }

    public boolean isAdmin() {
        return hasAuthority(ADMIN);
    }

    public boolean isSupperAdmin() {
        return hasAuthority(SUPPER_ADMIN);
    }
}
