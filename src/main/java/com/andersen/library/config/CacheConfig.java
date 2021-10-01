package com.andersen.library.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.cache.CacheManager;
import javax.cache.configuration.Configuration;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@org.springframework.context.annotation.Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    public static final String USER_BY_ID_AND_NAME_CACHE = "userCache";

    public static final String AUTHOR_BY_ID_CACHE = "authorCache";

    public static final String BOOK_BY_ID_CACHE = "bookCache";

    public static final String CLIENT_BY_ID_CACHE = "clientCache";

    public static final String PUBLISHING_HOUSE_BY_ID_CACHE = "publishingHouseCache";

    private final Configuration<Object, Object> jcacheEternalConfiguration;

    public CacheConfig() {
        jcacheEternalConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(1000))
                        .withExpiry(
                                ExpiryPolicyBuilder.timeToLiveExpiration(Duration.of(5, ChronoUnit.MINUTES))
                        )
                        .build()
        );
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, USER_BY_ID_AND_NAME_CACHE, jcacheEternalConfiguration);
            createCache(cm, AUTHOR_BY_ID_CACHE, jcacheEternalConfiguration);
            createCache(cm, BOOK_BY_ID_CACHE, jcacheEternalConfiguration);
            createCache(cm, CLIENT_BY_ID_CACHE, jcacheEternalConfiguration);
            createCache(cm, PUBLISHING_HOUSE_BY_ID_CACHE, jcacheEternalConfiguration);
        };
    }

    private void createCache(CacheManager cm, String cacheName, Configuration<Object, Object> configuration) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, configuration);
        }
    }

}
