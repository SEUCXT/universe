package com.seu.universe.config;

import java.util.HashMap;

public class ViewObject extends HashMap<String, Object> {

    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String DATA = "data";

    public ViewObject set(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Object get(String key) {
        return super.get(key);
    }
}
