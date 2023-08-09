package com.example.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheEvicter {

    @CacheEvict(value = "", allEntries = true)
    public void evictAllCacheValues() {}
}
