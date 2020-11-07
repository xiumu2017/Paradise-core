package com.paradise.core.security.config;

import com.paradise.core.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis配置类
 *
 * @author paradise
 * @date 2020/3/2
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
