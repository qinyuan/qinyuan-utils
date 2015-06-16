package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;

/**
 * Tag about handlebars template
 * Created by qinyuan on 15-6-16.
 */
public class HandlebarsTemplateTag extends MyTagSupport {
    @Override
    public int doStartTag() throws JspException {
        print("<script id=\"" + this.getId() + "\" type=\"text/x-handlebars-template\">");
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        print("</script>");
        return EVAL_PAGE;
    }
}
