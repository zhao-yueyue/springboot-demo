package com.ml.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JedisUtil {
    /*@Autowired
    private JedisPool jedisPool;

    *//*
     * 获得连接
     *
     * @return
     *//*
    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    *//*
     * 设值
     * @param key
     * @param value
     * @return
     *//*
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);
        } catch (Exception e) {
            log.error("Jedis set key:{} value:{} error", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 设值，含过期时间
     * @param key
     * @param value
     * @param expireTime 过期时间, 单位: s
     * @return
     *//*
    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("Jedis set key:{} value:{} expireTime:{} error", key, value, expireTime, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 根据key取值
     * @param key
     * @return
     *//*
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("Jedis get key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 删除key
     *
     * @param key
     * @return
     *//*
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key.getBytes());
        } catch (Exception e) {
            log.error("Jedis del key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 判断key是否存在
     *
     * @param key
     * @return
     *//*
    public Boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key.getBytes());
        } catch (Exception e) {
            log.error("Jedis exists key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 设值key过期时间
     *
     * @param key
     * @param expireTime 过期时间, 单位: s
     * @return
     *//*
    public Long expire(String key, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key.getBytes(), expireTime);
        } catch (Exception e) {
            log.error("Jedis expire key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 获取剩余时间
     *
     * @param key
     * @return
     *//*
    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);
        } catch (Exception e) {
            log.error("Jedis ttl key:{} error", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    *//*
     * 关闭jedis连接
     *
     * @param jedis
     * @return
     *//*
    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }*/
}