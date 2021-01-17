package com.niu.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * websocket 配置
 *
 * @author [nza]
 * @version 1.0 2021/1/17
 * @createTime 13:58
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 配置客户端连接断点
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
