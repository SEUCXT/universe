package com.seu.universe.service.Impl;

import com.seu.universe.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            //jedis.expire(key, 300);
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

    @Override
    public long saddSetValue(String key, String val) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            return jedis.sadd(key, val);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public Set<String> smembersSetValue(String key) {
        Jedis jedis = this.jedisPool.getResource();
        Set<String> set = new HashSet<>();
        try {
            set = jedis.smembers(key);
            return set;
        } catch (Exception e) {
            e.printStackTrace();
            return set;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public long sremSetValue(String key, String val) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            return jedis.srem(key, val);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public boolean sismemberSetValue(String key, String val) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            return jedis.sismember(key, val);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public long scardSetValue(String key) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            return jedis.scard(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public boolean hmsetHash(String key, Map<String, String> map) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            String status = jedis.hmset(key, map);
            return "ok".equals(status);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Map<String, String> map = new HashMap<>();
        Jedis jedis = this.jedisPool.getResource();
        try {
            map = jedis.hgetAll(key);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return map;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public long delKey(String key) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            return jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    @Override
    public long spopKey(String key) {
        Jedis jedis = this.jedisPool.getResource();
        try {
            String res = jedis.spop(key);
            return Long.parseLong(res);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
}
