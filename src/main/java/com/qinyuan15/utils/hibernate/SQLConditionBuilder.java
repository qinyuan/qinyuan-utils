package com.qinyuan15.utils.hibernate;

import com.google.common.base.Joiner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to build SQL condition
 * Created by qinyuan on 15-5-26.
 */
class SQLConditionBuilder {
    private final List<String> filters = new ArrayList<>();
    private final List<String> orders = new ArrayList<>();
    private final List<String> groups = new ArrayList<>();

    /**
     * add filter condition
     *
     * @param filter filter clause such as "hello=:hello"
     * @return Object itself
     */
    public SQLConditionBuilder addFilter(String filter) {
        if (!StringUtils.hasText(filter)) {
            return this;
        }

        filter = filter.trim();
        if (!(filter.startsWith("(") && filter.endsWith(")"))) {
            filter = "(" + filter + ")";
        }
        filters.add(filter);
        return this;
    }

    public SQLConditionBuilder addEqualFilter(String field) {
        return this.addFilter(field + "=:" + field);
    }

    public SQLConditionBuilder addOrder(String field, boolean asc) {
        if (!StringUtils.hasText(field)) {
            return this;
        }

        if (asc) {
            orders.add(field + " ASC");
        } else {
            orders.add(field + " DESC");
        }
        return this;
    }

    public SQLConditionBuilder addGroup(String field) {
        if (!StringUtils.hasText(field)) {
            return this;
        }

        groups.add(field);

        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();

        if (filters.size() > 0) {
            sb.append(" WHERE ");
            sb.append(Joiner.on(" AND ").join(filters));
        }

        if (groups.size() > 0) {
            sb.append(" GROUP BY ");
            sb.append(Joiner.on(",").join(groups));
        }

        if (orders.size() > 0) {
            sb.append(" ORDER BY ");
            sb.append(Joiner.on(",").join(orders));
        }

        return sb.toString();
    }
}
