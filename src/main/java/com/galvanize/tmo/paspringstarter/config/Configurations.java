package com.galvanize.tmo.paspringstarter.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Bean
    public CacheManager getCacheManger() {
        return new ConcurrentMapCacheManager("books");
    }
}
