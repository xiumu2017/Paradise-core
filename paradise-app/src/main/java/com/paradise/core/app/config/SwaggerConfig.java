package com.paradise.core.app.config;

import com.paradise.core.common.config.BaseSwaggerConfig;
import com.paradise.core.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2API文档的配置
 *
 * @author Paradise
 * @date 2018/4/26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.paradise.core.app.controller")
                .title("英语阅读小程序 - APP")
                .description("英语阅读小程序接口文档")
                .contactName("Paradise")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
