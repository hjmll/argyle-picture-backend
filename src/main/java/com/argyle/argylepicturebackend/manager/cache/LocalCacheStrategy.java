package com.argyle.argylepicturebackend.manager.cache;

import com.github.benmanes.caffeine.cache.Cache;

public class LocalCacheStrategy implements CacheStrategy {
    private final Cache<String, String> localCache;

    public LocalCacheStrategy(Cache<String, String> localCache) {
        this.localCache = localCache;
    }

    @Override
    public String get(String key) {
        return localCache.getIfPresent(key);
    }

    @Override
    public void put(String key, String value) {
        localCache.put(key, value);
    }
}
