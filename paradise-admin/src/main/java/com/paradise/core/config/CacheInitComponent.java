package com.paradise.core.config;

import com.paradise.core.common.constant.RedisKeyConstant;
import com.paradise.core.common.service.RedisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 初始化缓存
 *
 * @author Paradise
 */
@Slf4j
@Component
@AllArgsConstructor
public class CacheInitComponent {
    private final RedisService redisService;

    @PostConstruct
    public void initZone() {
        log.info("开始初始化加载区域服务中心到缓存...");
        redisService.del(RedisKeyConstant.ALL_ENABLE_ZONE_ID);
        redisService.lPushAll(RedisKeyConstant.ALL_ENABLE_ZONE_ID, 1);
        List<Object> objectList = redisService.lRange(RedisKeyConstant.ALL_ENABLE_ZONE_ID, 0, -1);
        log.info("缓存中的数据：{}", objectList);
    }
}
