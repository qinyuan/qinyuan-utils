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
        print("<script src=\"");
        if (!src.startsWith(PREFIX)) {
            print(JS_PREFIX);
        }
        print(src);

        if (!src.endsWith(".js")) {
            print(".js");
        }

        printVersion();
        print("\"></script>");
        return EVAL_PAGE;
    }
}
