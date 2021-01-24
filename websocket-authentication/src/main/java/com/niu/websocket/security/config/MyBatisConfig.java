package com.niu.websocket.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis相关配置
 */
@Configuration
@MapperScan({"com.niu.websocket.security.dao"})
public class MyBatisConfig {
}
