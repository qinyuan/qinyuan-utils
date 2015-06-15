package com.qinyuan15.utils.config;

import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;

/**
 * Dao of AppConfig
 * Created by qinyuan on 15-6-15.
 */
public class AppConfigDao {
    public void save(String name, String value) {
        AppConfig appConfig = getInstanceByName(name);
        if (appConfig == null) {
            appConfig = new AppConfig();
            appConfig.setPropertyName(name);
            appConfig.setPropertyValue(value);
            HibernateUtils.save(appConfig);
        } else {
            appConfig.setPropertyValue(value);
            HibernateUtils.update(appConfig);
        }
    }

    private AppConfig getInstanceByName(String name) {
        return new HibernateListBuilder().addFilter("propertyName=:propertyName")
                .addArgument("propertyName", name).getFirstItem(AppConfig.class);
    }

    public String get(String name) {
        AppConfig appConfig = getInstanceByName(name);
        return appConfig == null ? null : appConfig.getPropertyValue();
    }
}
