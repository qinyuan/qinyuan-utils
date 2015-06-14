package com.qinyuan15.utils.mvc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qinyuan15.utils.mvc.UrlUtils;
import com.qinyuan15.utils.security.SecuritySearcher;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected SecuritySearcher securitySearcher;

    protected String redirect(String page) {
        return "redirect:" + page;
    }

    protected String redirect(String page, String errorInfo) {
        return redirect(addErrorInfoParameter(page, errorInfo));
    }

    private String addErrorInfoParameter(String url, String errorInfo) {
        if (url.contains("?")) {
            url += "&";
        } else {
            url += "?";
        }
        return url + "errorInfo=" + UrlUtils.encode(errorInfo);
    }

    protected String getLocalAddress() {
        return request.getLocalAddr();
    }

    protected String getParameter(String name) {
        return request.getParameter(name);
    }

    protected void setTitle(Object title) {
        request.setAttribute("title", title);
    }

    protected String toJson(Object obj) {
        GsonBuilder builder = new GsonBuilder();
        if (this.request.getParameter("pretty") != null) {
            builder.setPrettyPrinting();
        }

        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    protected boolean isPositive(Integer intValue) {
        return intValue != null && intValue > 0;
    }

    protected boolean isPositive(String strValue) {
        return NumberUtils.isNumber(strValue) && NumberUtils.toInt(strValue) > 0;
    }

    protected void addCss(String file) {
        addListAttribute("moreCss", file);
    }

    protected void addJs(String file) {
        addListAttribute("moreJs", file);
    }

    protected void addHeadJs(String file) {
        addListAttribute("headJs", file);
    }

    protected void addIEJs(String file) {
        addListAttribute("ieJs", file);
    }

    protected void addCssAndJs(String file) {
        addCss(file);
        addJs(file);
    }

    @SuppressWarnings("unchecked")
    protected void addListAttribute(String key, String value) {
        if (request.getAttribute(key) == null) {
            request.setAttribute(key, new ArrayList<String>());
        }
        ((List) request.getAttribute(key)).add(value);
    }

    private static Map<String, Object> createResultMap(boolean success, Object detail) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("detail", detail);
        return map;
    }

    protected String success() {
        return toJson(createResultMap(true, null));
    }

    protected String fail(String info) {
        return toJson(createResultMap(false, info));
    }
}
