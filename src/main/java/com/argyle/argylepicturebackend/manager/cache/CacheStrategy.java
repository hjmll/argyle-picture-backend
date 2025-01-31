package com.argyle.argylepicturebackend.manager.cache;

public interface CacheStrategy {
    String get(String key);
    void put(String key, String value);
}
