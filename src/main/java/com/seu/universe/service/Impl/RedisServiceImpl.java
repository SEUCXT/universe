package com.seu.universe.service.Impl;

import com.seu.universe.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis = this.jedisPool.getResource();
        String ret;
        try {
            ret = jedis.get(key);
        } finally {
            if (jedis != null)
                jedis.close();
        }
        return ret;
    }

    @Override
    public boolean set(String key, String val) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            jedis.set(key, val);
            jedis.expire(key, 300);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null)
                jedis.close();
            return true;
        }
    }
}
