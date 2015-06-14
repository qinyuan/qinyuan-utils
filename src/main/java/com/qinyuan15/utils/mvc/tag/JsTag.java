package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

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
        try {
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

            this.pageContext.getOut().print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
