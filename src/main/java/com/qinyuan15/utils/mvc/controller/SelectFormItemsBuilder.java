package com.qinyuan15.utils.mvc.controller;

import com.qinyuan15.utils.database.hibernate.PersistObject;
import org.apache.commons.beanutils.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectFormItemsBuilder {

    public List<SelectFormItem> build(List<? extends PersistObject> persistObjects, String valueField) {
        try {
            List<SelectFormItem> items = new ArrayList<>();
            if (persistObjects == null || persistObjects.size() == 0) {
                return items;
            }

            for (PersistObject persistObject : persistObjects) {
                SelectFormItem item = new SelectFormItem();
                item.id = persistObject.getId();
                item.value = BeanUtils.getProperty(persistObject, valueField);
                items.add(item);
            }
            return items;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class SelectFormItem {
        private Integer id;
        private String value;

        public Integer getId() {
            return id;
        }

        public String getValue() {
            return value;
        }
    }
}
