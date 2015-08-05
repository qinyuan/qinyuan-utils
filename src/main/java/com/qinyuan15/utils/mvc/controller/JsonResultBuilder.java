package com.qinyuan15.utils.mvc.controller;

import java.util.HashMap;
import java.util.Map;

public class JsonResultBuilder {
    public static Map<String, Object> build(boolean success, Object detail) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", success);
        map.put("detail", detail);
        return map;
    }
}
