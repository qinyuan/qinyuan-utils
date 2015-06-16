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
        StringBuilder sb = new StringBuilder("<link href=\"");

        if (!href.startsWith(PREFIX)) {
            sb.append(CSS_PREFIX);
        }
        sb.append(href);

        if (!href.endsWith(".css")) {
            sb.append(".css");
        }

        sb.append(getVersion());
        sb.append("\" rel=\"stylesheet\"/>");

        print(sb);
        return EVAL_PAGE;
    }
}
