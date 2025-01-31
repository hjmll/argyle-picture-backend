package com.argyle.argylepicturebackend.manager.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

public class RedisCacheStrategy implements CacheStrategy {
    private final StringRedisTemplate stringRedisTemplate;

    public RedisCacheStrategy(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String get(String key) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        return valueOps.get(key);
    }

    @Override
    public void put(String key, String value) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        int cacheExpireTime = 300 + (int) (Math.random() * 300);
        valueOps.set(key, value, cacheExpireTime, TimeUnit.SECONDS);
    }
}
