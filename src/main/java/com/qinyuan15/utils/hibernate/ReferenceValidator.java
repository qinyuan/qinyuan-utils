package com.qinyuan15.utils.hibernate;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Validate if certain instance of certain table is used by other table
 */
public class ReferenceValidator {
    private List<Pair<String, String>> references = new ArrayList<>();

    public ReferenceValidator add(String referentialTable, String referentialField) {
        if (!StringUtils.hasText(referentialTable)) {
            throw new IllegalArgumentException("referentialTable is empty");
        }

        if (!StringUtils.hasText(referentialField)) {
            throw new IllegalArgumentException("referentialField is empty");
        }

        references.add(Pair.of(referentialTable, referentialField));
        return this;
    }

    public ReferenceValidator add(Class<?> clazz, String referentialField) {
        return add(clazz.getSimpleName(), referentialField);
    }

    public boolean isUsed(int id) {
        for (Pair<String, String> reference : references) {
            int count = new HibernateListBuilder().addEqualFilter(reference.getRight(), id)
                    .count(reference.getLeft());
            if (count > 0) {
                return true;
            }
        }
        return false;
    }
}
