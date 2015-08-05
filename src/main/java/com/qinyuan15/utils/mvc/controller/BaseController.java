package com.qinyuan15.utils.mvc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qinyuan15.utils.mvc.UrlUtils;
import com.qinyuan15.utils.security.SecuritySearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    protected final static String BLANK_PAGE = "blank";

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpSession session;

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

    public void addCss(String file) {
        addCss(file, true);
    }

    public void addCss(String file, boolean version) {
        addListAttribute("moreCss", new Resource(file, version));
    }

    public void addJs(String file) {
        addJs(file, true);
    }

    public void addJs(String file, boolean version) {
        addListAttribute("moreJs", new Resource(file, version));
    }

    public void addHeadJs(String file) {
        addHeadJs(file, true);
    }

    public void addHeadJs(String file, boolean version) {
        addListAttribute("headJs", new Resource(file, version));
    }

    public void addIEJs(String file) {
        addIEJs(file, true);
    }

    public void addIEJs(String file, boolean version) {
        addListAttribute("ieJs", new Resource(file, version));
    }

    public void addCssAndJs(String file, boolean version) {
        addCss(file, version);
        addJs(file, version);
    }

    public void addCssAndJs(String file) {
        addCss(file);
        addJs(file);
    }

    private void addListAttribute(String key, Resource value) {
        if (request.getAttribute(key) == null) {
            request.setAttribute(key, new ArrayList<Resource>());
        }
        @SuppressWarnings("unchecked")
        List<Resource> resources = (List) request.getAttribute(key);
        resources.add(value);
    }

    public void addJavaScriptData(String key, Object value) {
        final String mapKey = "javascriptDatas";
        if (request.getAttribute(mapKey) == null) {
            request.setAttribute(mapKey, new HashMap<String, String>());
        }
        @SuppressWarnings("unchecked")
        Map<String, String> datas = (Map) request.getAttribute(mapKey);
        datas.put(key, toJson(value));
    }

    public void setAttribute(String key, Object value) {
        request.setAttribute(key, value);
    }

    public void setAttributeAndJavaScriptData(String key, Object value) {
        setAttribute(key, value);
        addJavaScriptData(key, value);
    }

    protected String success() {
        return toJson(JsonResultBuilder.build(true, null));
    }

    protected String success(String detail) {
        return toJson(JsonResultBuilder.build(true, detail));
    }

    protected String fail(String info) {
        return toJson(JsonResultBuilder.build(false, info));
    }

    protected String failByDatabaseError() {
        return fail("数据库操作失败！");
    }

    protected String failByInvalidParam() {
        return fail("请求参数无效！");
    }

    protected boolean validateIdentityCode(String identityCode) {
        if (!StringUtils.hasText(identityCode)) {
            return false;
        }

        String identityCodeInSession = (String) session.getAttribute(IdentityCodeController.IDENTITY_CODE_SESSION_KEY);

        return StringUtils.hasText(identityCodeInSession) &&
                identityCode.toLowerCase().equals(identityCodeInSession.toLowerCase());
    }

    public static class Resource {
        private final String href;
        private final boolean version;

        private Resource(String href, boolean version) {
            this.href = href;
            this.version = version;
        }

        public String getHref() {
            return href;
        }

        public boolean isVersion() {
            return version;
        }
    }
}
