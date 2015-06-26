package com.qinyuan15.utils.mvc.tag;


import javax.servlet.jsp.JspException;

/**
 * Tag class of Spring MVC login form
 * Created by qinyuan on 15-6-26.
 */
public class SpringLoginForm extends MyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        print("<form method=\"post\" ");
        printId();
        print("action=\"j_spring_security_check\">");
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        print("</form>");
        return EVAL_PAGE;
    }
}
