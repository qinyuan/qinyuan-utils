package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;

/**
 * Tag class of js
 * Created by qinyuan on 15-6-14.
 */
public class JsTag extends CssJsBaseTag {
    private String src;

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public int doEndTag() throws JspException {
        StringBuilder sb = new StringBuilder("<script src=\"");
        if (!src.startsWith(PREFIX)) {
            sb.append(JS_PREFIX);
        }
        sb.append(src);

        if (!src.endsWith(".js")) {
            sb.append(".js");
        }

        sb.append(getVersion());
        sb.append("\"></script>");

        print(sb);
        return EVAL_PAGE;
    }
}
