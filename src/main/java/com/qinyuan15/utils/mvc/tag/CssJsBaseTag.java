package com.qinyuan15.utils.mvc.tag;

/**
 * Base Class of CssTag and JsTag
 * Created by qinyuan on 15-6-14.
 */
public class CssJsBaseTag extends MyTagSupport {
    private final static long VERSION = System.currentTimeMillis();
    private boolean version;
    protected final static String PREFIX = "resources";
    protected final static String CSS_PREFIX = PREFIX + "/css/";
    protected final static String JS_PREFIX = PREFIX + "/js/";

    public void setVersion(boolean version) {
        this.version = version;
    }

    protected void printVersion() {
        if (version) {
            print("?=" + VERSION);
        }
    }
}
