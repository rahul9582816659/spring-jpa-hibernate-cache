package com.hibernate.cache.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(ehCache());
    }

    @Bean
    public CacheManager ehCache() {
        CacheManager cacheManager = CacheManager.create();

        Cache employeeCache = new Cache(
                new CacheConfiguration("com.hibernate.cache.entity.Employee", 500)
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
                        .eternal(false)
                        .timeToLiveSeconds(60 * 60 * 24)
                        .timeToIdleSeconds(60 * 60 * 24)
                        .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.NONE))
        );
        cacheManager.addCache(employeeCache);

        return cacheManager;
    }
}
