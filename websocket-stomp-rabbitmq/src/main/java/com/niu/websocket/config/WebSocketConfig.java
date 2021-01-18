package com.niu.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
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
@EnableConfigurationProperties({StompProperties.class})
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private StompProperties stompProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 配置客户端连接断点
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        String[] enablePrefix = new String[]{"/topic/", "/queue/", "/exchange/"};

        // 配置 需要 Stomp 代理的前缀
        registry.enableStompBrokerRelay(enablePrefix)
                .setRelayHost(stompProperties.getHost())
                .setRelayPort(stompProperties.getPort())
                .setClientLogin(stompProperties.getUsername())
                .setClientPasscode(stompProperties.getPassword())
                .setVirtualHost(stompProperties.getVirtualhost());
    }
}
