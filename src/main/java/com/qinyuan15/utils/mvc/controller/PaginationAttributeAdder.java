package com.qinyuan15.utils.mvc.controller;

import com.google.common.base.Joiner;
import com.qinyuan15.utils.IntegerUtils;
import com.qinyuan15.utils.mvc.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Class to add attributes about pagination to ModelMap
 * Created by qinyuan on 15-4-5.
 */
public class PaginationAttributeAdder {
    private final static Logger LOGGER = LoggerFactory.getLogger(PaginationAttributeAdder.class);

    public final static int DEFAULT_PAGE_SIZE = 50;
    public final static int DEFAULT_VISIBLE_BUTTON_SIZE = 10;
    public final static String PAGE_NUMBER_KEY = "pageNumber";
    public final static String DEFAULT_ROW_ITEMS_NAME = "paginationItems";
    public final static String DEFAULT_ROW_ITEM_COUNT_NAME = "paginationItemCount";

    private PaginationItemFactory factory;
    private HttpServletRequest request;
    private String rowItemsName = DEFAULT_ROW_ITEMS_NAME;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int visibleButtonSize = DEFAULT_VISIBLE_BUTTON_SIZE;
    private String rowItemCountName = DEFAULT_ROW_ITEM_COUNT_NAME;

    public PaginationAttributeAdder(PaginationItemFactory factory, HttpServletRequest request) {
        this.factory = factory;
        this.request = request;
    }

    public PaginationAttributeAdder setPageSize(int pageSize) {
        if (!IntegerUtils.isPositive(pageSize)) {
            pageSize = Integer.MAX_VALUE;
        }
        this.pageSize = pageSize;
        return this;
    }

    public PaginationAttributeAdder setRowItemCountName(String rowItemCountName) {
        this.rowItemCountName = rowItemCountName;
        return this;
    }

    public PaginationAttributeAdder setVisibleButtonSize(int visibleButtonSize) {
        if (!IntegerUtils.isPositive(visibleButtonSize)) {
            visibleButtonSize = Integer.MAX_VALUE;
        }

        this.visibleButtonSize = visibleButtonSize;
        return this;
    }

    public PaginationAttributeAdder setRowItemsName(String rowItemsName) {
        this.rowItemsName = rowItemsName;
        return this;
    }

    private Integer getIntParameter(String name) {
        String value = request.getParameter(name);
        if (value == null) {
            return null;
        }

        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            LOGGER.error("fail to convert '{}' to integer, info: '{}'", value, e);
            return null;
        }
    }

    public void add() {
        Integer pageNumber = getIntParameter(PAGE_NUMBER_KEY);

        if (!IntegerUtils.isPositive(pageNumber)) {
            pageNumber = 1;
        }

        long itemCount = factory.getCount();
        int pageCount = PaginationUtils.getPageCount(itemCount, pageSize);
        if (pageNumber > pageCount) {
            pageNumber = pageCount;
        }

        String pageUrl = request.getRequestURI();

        List<String> parameters = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Set<Map.Entry<String, Object>> entries = request.getParameterMap().entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            if (entry.getKey().equals(PAGE_NUMBER_KEY)) {
                continue;
            }

            Object value = entry.getValue();
            if (value == null) {
                continue;
            }

            if (value instanceof String) {
                parameters.add(entry.getKey() + "=" + UrlUtils.encode((String) value));
            } else if (value.getClass().isArray()) {
                String[] strings = (String[]) value;
                if (strings.length > 0) {
                    parameters.add(entry.getKey() + "=" + UrlUtils.encode(strings[0]));
                }
            }
        }
        if (parameters.size() > 0) {
            pageUrl += "?" + Joiner.on("&").join(parameters);
        }

        List<PaginationAnchor> anchors = PaginationAnchor.create(
                pageUrl, pageCount, visibleButtonSize, pageNumber);

        request.setAttribute("paginationAnchors", anchors);
        request.setAttribute("rowStartIndex", (pageNumber - 1) * pageSize + 1);
        request.setAttribute(rowItemsName, factory.getInstances((pageNumber - 1) * pageSize, pageSize));
        request.setAttribute(rowItemCountName, itemCount);
    }
}
