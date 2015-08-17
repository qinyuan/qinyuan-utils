package com.qinyuan15.utils.database.hibernate;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class of HibernateProperty
 * Created by qinyuan on 15-5-29.
 */
public class HibernatePropertyUtils {
    private final static Properties properties = HibernateUtils.getConfiguration().getProperties();

    public static String getUsername() {
        return properties.getProperty("connection.username");
    }

    public static String getPassword() {
        return properties.getProperty("connection.password");
    }

    public static String getUrl() {
        return properties.getProperty("connection.url");
    }

    private final static Pattern hostPattern = Pattern.compile("(?<=jdbc:mysql://)[^:]+");

    public static String getHost() {
        Matcher matcher = hostPattern.matcher(getUrl());
        return matcher.find() ? matcher.group() : null;
    }

    private final static Pattern databasePattern = Pattern.compile("[^/]+$");

    public static String getDatabase() {
        String url = getUrl();
        while (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }

        Matcher matcher = databasePattern.matcher(url);
        return matcher.find() ? matcher.group() : null;
    }
}
