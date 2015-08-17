package com.qinyuan15.utils.config;

import com.qinyuan15.utils.database.hibernate.PersistObject;

/**
 * Class of application configuration
 * Created by qinyuan on 15-6-15.
 */
public class AppConfig extends PersistObject {
    private String propertyName;
    private String propertyValue;

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
