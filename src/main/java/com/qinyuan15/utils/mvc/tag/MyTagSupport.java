package com.qinyuan15.utils.mvc.tag;

import javax.servlet.jsp.tagext.TagSupport;

/**
 * Class to extends TagSupport
 * Created by qinyuan on 15-6-16.
 */
public class MyTagSupport extends TagSupport {
    protected void print(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            this.pageContext.getOut().print(obj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
