package com.qinyuan15.utils.database.hibernate;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Tool class of PersistObject
 * Created by qinyuan on 15-3-14.
 */
public class PersistObjectUtils {
    private PersistObjectUtils() {
    }

    /**
     * Get IDs of each persistObject of persistObject list
     *
     * @param persistObjects persistObject list
     * @return a list whose elements are IDs of element in persistObject list
     */
    public static List<Integer> getIds(List<? extends PersistObject> persistObjects) {
        List<Integer> ids = new ArrayList<>();
        if (persistObjects == null) {
            return ids;
        }

        for (PersistObject persistObject : persistObjects) {
            ids.add(persistObject.getId());
        }
        return ids;
    }

    /**
     * Get IDs of each persistObject of persistObject list,
     * then join them as a string
     *
     * @param persistObjects persistObject list
     * @return a string containing IDs of element in persistObject list
     */
    public static String getIdsString(List<? extends PersistObject> persistObjects) {
        List<Integer> ids = getIds(persistObjects);
        return StringUtils.join(ids, ",");
    }

}
