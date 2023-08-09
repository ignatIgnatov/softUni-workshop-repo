package com.example.cache;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveCacheScheduler {

    private final CacheEvicter cacheEvicter;

    public RemoveCacheScheduler(CacheEvicter cacheEvicter) {
        this.cacheEvicter = cacheEvicter;
    }

    @Scheduled(cron = "0 0 */2 * * *") // fixedDelay = 7200000
    public void removeCacheEveryTwoHours() {
        this.cacheEvicter.evictAllCacheValues();
    }
}
