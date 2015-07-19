package com.qinyuan15.utils.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityUtils.class);

    public static String getUsername() {
        UserDetails userDetails = getUserDetails();
        return userDetails == null ? null : userDetails.getUsername();
    }

    public static UserDetails getUserDetails() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserDetails) {
            return (UserDetails) userDetails;
        } else {
            LOGGER.info("userDetails is String, whose value is {}", userDetails);
            return null;
        }
    }

    public static List<String> getAuthorities() {
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

    public static boolean hasAuthority(String roleName) {
        return getAuthorities().contains(roleName);
    }
}
