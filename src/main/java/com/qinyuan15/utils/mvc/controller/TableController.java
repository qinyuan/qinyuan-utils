package com.qinyuan15.utils.mvc.controller;

import org.springframework.util.StringUtils;

public abstract class TableController extends ImageController {
    protected abstract DatabaseTable getTable();

    protected String getDistinctValues() {
        String alias = getParameter("alias");
        if (!StringUtils.hasText(alias)) {
            return failByInvalidParam();
        }

        return toJson(getTableUtil().getDistinctValues(getTable(), alias));
    }

    protected String addFilter(/*@RequestParam(value = "filterValues[]", required = false) String[] filterValues*/) {
        String filterField = getParameter("filterField");
        String[] filterValues = getParameters("filterValues");
        if (!StringUtils.hasText(filterField)) {
            return failByInvalidParam();
        }
        getTableUtil().addFilter(filterField, filterValues);
        return success();
    }

    protected String removeFilter() {
        String filterField = getParameter("filterField");
        if (!StringUtils.hasText(filterField)) {
            return failByInvalidParam();
        }
        getTableUtil().removeFilter(filterField);
        return success();
    }

    protected MVCTableUtil getTableUtil() {
        return new MVCTableUtil(request, this.getClass());
    }
}
