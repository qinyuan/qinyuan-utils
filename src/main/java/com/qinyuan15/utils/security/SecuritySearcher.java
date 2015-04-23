package com.qinyuan15.utils.security;

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
        return getUserDetails().getUsername();
    }

    public UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public List<String> getAuthorities() {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority authority : getUserDetails().getAuthorities()) {
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
