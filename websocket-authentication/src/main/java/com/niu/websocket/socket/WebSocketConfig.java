package com.niu.websocket.socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author Zian.Niu
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties({StompProperties.class})
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private SocketChanelInterceptor socketChanelInterceptor;

    @Autowired
    private StompProperties stompProperties;

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        //客户端连接端点
        registry.addEndpoint("/websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        String[] enablePrefix = new String[]{"/topic/", "/queue/", "/exchange/", "/amq/queue/"};

        // 配置 需要 Stomp 代理的前缀
        registry.enableStompBrokerRelay(enablePrefix)
                .setRelayHost(stompProperties.getHost())
                .setRelayPort(stompProperties.getPort())
                .setClientLogin(stompProperties.getUsername())
                .setClientPasscode(stompProperties.getPassword())
                .setVirtualHost(stompProperties.getVirtualhost());

        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 配置客户端入站通道拦截器，用于传递从WebSocket客户端接收到的消息
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(socketChanelInterceptor);
    }

    /**
     * 配置客户端出站通道拦截器，用于向WebSocket客户端发送服务器消息
     */
    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        //registration.interceptors(socketChanelInterceptor);
    }
}
