package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;

/**
 * Tag class of css
 * Created by qinyuan on 15-6-14.
 */
public class CssTag extends CssJsBaseTag {

    private String href;

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public int doEndTag() throws JspException {
        print("<link href=\"");

        if (!href.startsWith(PREFIX)) {
            print(CSS_PREFIX);
        }
        print(href);

        if (!href.endsWith(".css")) {
            print(".css");
        }

        printVersion();
        print("\" rel=\"stylesheet\"/>");
        return EVAL_PAGE;
    }
}
