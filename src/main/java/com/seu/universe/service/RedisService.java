package com.seu.universe.service;

public interface RedisService {

    String get(String key);

    boolean set(String key, String val);
}
