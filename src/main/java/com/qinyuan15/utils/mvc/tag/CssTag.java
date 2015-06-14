package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

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
        try {
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

            this.pageContext.getOut().print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
}
