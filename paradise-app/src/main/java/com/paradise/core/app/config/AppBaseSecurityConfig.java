package com.paradise.core.app.config;

import com.paradise.core.app.service.MpMemberService;
import com.paradise.core.security.config.BaseSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * mall-security模块相关配置
 *
 * @author Paradise
 * @date 2019/11/5
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppBaseSecurityConfig extends BaseSecurityConfig {

    private final MpMemberService memberService;

    public AppBaseSecurityConfig(@Lazy MpMemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return memberService::loadUserByOpenId;
    }
}
