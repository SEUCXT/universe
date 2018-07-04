package com.seu.universe.service;

import java.util.Map;
import java.util.Set;

public interface RedisService {

    /**
     * 根据key获取redis的value
     * @param key
     * @return
     */
    String get(String key);

    /**
     * redis中添加 key value
     * @param key
     * @param val
     * @return
     */
    boolean set(String key, String val);

    /**
     * 往集合中添加value
     * @param key
     * @param val
     * @return
     */
    long saddSetValue(String key, String val);

    /**
     * 获取集合中的所有元素
     * @param key
     * @return
     */
    Set<String> smembersSetValue(String key);

    /**
     * 移除set中的元素
     * @param key
     * @param val
     * @return
     */
    long sremSetValue(String key, String val);

    /**
     * 判断集合中是否包含某个元素
     * @param key
     * @param val
     * @return
     */
    boolean sismemberSetValue(String key, String val);

    /**
     * 获取集合的大小
     * @param key
     * @return
     */
    long scardSetValue(String key);

    /**
     * redis添加hash对象
     * @param key
     * @param map
     * @return
     */
    boolean hmsetHash(String key, Map<String, String> map);

    /**
     * 获取redis的hash对象
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * 删除key
     * @param key
     * @return
     */
    long delKey(String key);
}
