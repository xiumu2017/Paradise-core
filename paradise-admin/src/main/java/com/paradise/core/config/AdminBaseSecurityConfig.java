package com.paradise.core.config;

import com.paradise.core.model.UmsResource;
import com.paradise.core.security.component.DynamicSecurityService;
import com.paradise.core.security.config.BaseSecurityConfig;
import com.paradise.core.service.UmsAdminService;
import com.paradise.core.service.UmsResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 *
 * @author paradise
 * @date 2019/11/9
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminBaseSecurityConfig extends BaseSecurityConfig {

    private final UmsAdminService adminService;
    private final UmsResourceService resourceService;

    public AdminBaseSecurityConfig(@Lazy UmsAdminService adminService,
                                   @Lazy UmsResourceService resourceService) {
        this.adminService = adminService;
        this.resourceService = resourceService;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return adminService::loadUserByUsername;
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            List<UmsResource> resourceList = resourceService.listAll();
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(resourceList.size());
            for (UmsResource resource : resourceList) {
                map.put(resource.getUrl(), new SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }
}
