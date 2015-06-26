package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;

/**
 * Class of tag to remember login in Spring MVC
 * Created by qinyuan on 15-6-26.
 */
public class SpringRememberLoginTag extends MyTagSupport {
    @Override
    public int doEndTag() throws JspException {
        print("<input type=\"checkbox\" name=\"_spring_security_remember_me\"");
        printId();
        print("/> ");
        return EVAL_PAGE;
    }
}
