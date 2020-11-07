package com.paradise.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 *
 * @author Paradise
 * @date 2019/4/8
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.paradise.core.mapper","com.paradise.core.dao"})
public class MyBatisConfig {
}
