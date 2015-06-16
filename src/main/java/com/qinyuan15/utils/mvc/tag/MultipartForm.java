package com.qinyuan15.utils.mvc.tag;

import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;

/**
 * Tag class of multipart-form
 * Created by qinyuan on 15-6-16.
 */
public class MultipartForm extends MyTagSupport {

    private String action;

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder sb = new StringBuilder("<form method=\"post\" ");
        String id = this.getId();
        if(StringUtils.hasText(id)) {
            sb.append("id=\"");
            sb.append(id);
            sb.append("\" ");
        }
        sb.append("action=\"");
        sb.append(action);
        sb.append("\" enctype=\"multipart/form-data\">");

        print(sb);
        return EVAL_PAGE;
    }

    @Override
    public int doEndTag() throws JspException {
        print("</form>");
        return EVAL_PAGE;
    }
}
