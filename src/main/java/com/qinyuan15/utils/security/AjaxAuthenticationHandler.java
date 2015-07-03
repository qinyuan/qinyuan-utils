package com.qinyuan15.utils.security;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class AjaxAuthenticationHandler {
    protected void printJson(HttpServletResponse response, String json) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(json);
    }
}
