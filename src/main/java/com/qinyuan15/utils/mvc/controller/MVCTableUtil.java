package com.qinyuan15.utils.mvc.controller;

import com.google.common.collect.Lists;
import com.qinyuan15.utils.IntegerUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MVCTableUtil {
    private final HttpServletRequest request;
    private final HttpSession session;
    private final String sessionKey;

    public MVCTableUtil(HttpServletRequest request, String sessionKey) {
        this.request = request;
        this.session = request.getSession();
        this.sessionKey = sessionKey;
    }

    public MVCTableUtil(HttpServletRequest request, Class<?> clazz) {
        this(request, clazz.getSimpleName());
    }

    public void addIndexAttributes(DatabaseTable table) {
        String pageSizeString = request.getParameter("pageSize");
        Integer pageSize = IntegerUtils.isPositive(pageSizeString) ? Integer.parseInt(pageSizeString) : 10;
        String orderField = request.getParameter("orderField");
        String orderType = request.getParameter("orderType");
        if (StringUtils.hasText(orderField) && StringUtils.hasText(orderType)) {
            addOrder(table, orderField, orderType);
        }
        addFilters(table);

        request.setAttribute("table", table);
        new PaginationAttributeAdder(table, request).setPageSize(pageSize).add();
    }

    public MVCTableUtil addOrder(DatabaseTable table, String orderField, String orderType) {
        if (orderType != null && orderType.toLowerCase().equals("desc")) {
            table.addOrder(orderField, false);
        } else {
            table.addOrder(orderField, true);
        }
        return this;
    }

    public MVCTableUtil addFilters(DatabaseTable table) {
        for (Map.Entry<String, String[]> entry : getFilters().entrySet()) {
            table.addFilter(entry.getKey(), Lists.newArrayList(entry.getValue()));
        }
        return this;
    }

    private final static String NULL_STRING = "(空白)";

    public List<DistinctItem> getDistinctValues(DatabaseTable table, String alias) {
        List<DistinctItem> items = new ArrayList<>();
        for (Object value : table.getDistinctValues(alias)) {
            if (value == null) {
                value = NULL_STRING;
            }
            items.add(new DistinctItem(value, !isFiltered(alias, value)));
        }
        return items;
    }

    public void addFilter(String filterField, String[] filterValues) {
        if (!StringUtils.hasText(filterField)) {
            return;
        }

        if (filterValues == null) {
            filterValues = new String[0];
        }
        for (int i = 0; i < filterValues.length; i++) {
            if (NULL_STRING.equals(filterValues[i])) {
                filterValues[i] = null;
            }
        }

        getFilters().put(filterField, filterValues);
    }

    public void removeFilter(String filterField) {
        getFilters().remove(filterField);
    }


    /**
     * validate if certain value in certain field is filtered
     *
     * @param field field in database
     * @param value value of given field
     * @return true is value is filtered
     */
    private boolean isFiltered(String field, Object value) {
        String[] values = getFilters().get(field);
        if (values == null) {
            return false;
        }

        if (value.equals(NULL_STRING)) {
            for (String v : values) {
                if (v == null) {
                    return false;
                }
            }
        } else {
            String valueString = value.toString();
            for (String v : values) {
                if (v != null && v.equals(valueString)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class DistinctItem {
        public final Object text;
        public final boolean checked;

        DistinctItem(Object text, boolean checked) {
            this.text = text;
            this.checked = checked;
        }
    }

    private Map<String, String[]> getFilters() {
        @SuppressWarnings("unchecked")
        Map<String, String[]> filters = (Map) session.getAttribute(sessionKey);
        if (filters == null) {
            filters = new HashMap<>();
            session.setAttribute(sessionKey, filters);
        }
        return filters;
    }
}
