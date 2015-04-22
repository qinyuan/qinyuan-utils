package com.qinyuan15.utils.security;

//import com.qinyuan15.utils.dao.UserDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Tool class about security
 * Created by qinyuan on 15-3-6.
 *//*
public class SecurityUtils {
    public final static String ADMIN = "ROLE_ADMIN";
    public final static String SUPPER_ADMIN = "ROLE_SUPPER_ADMIN";

    private SecurityUtils() {
    }

    public static Integer getUserId() {
        return new UserDao().getIdByName(getUsername());
    }

    public static String getUsername() {
        return getUserDetails().getUsername();
    }

    public static UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static List<String> getAuthorities() {
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority authority : getUserDetails().getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return authorities;
    }

    public static boolean isAdmin() {
        return getAuthorities().contains(ADMIN);
    }

    public static boolean isSupperAdmin() {
        return getAuthorities().contains(SUPPER_ADMIN);
    }
}*/
