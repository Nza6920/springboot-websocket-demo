package com.niu.websocket.config;

import com.niu.websocket.client.ChatWebsocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Socket 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/01/22 15:18]
 * @createTime [2021/01/22 15:18]
 */
@Configuration
@Slf4j
public class WebSocketClientConfig {

    @Value("${websocket.chat.endpoint}")
    private String chatEndpoint;

    @Value("${websocket.chat.appId}")
    private String appId;

    @Bean
    public ChatWebsocketClient chatWebsocketClient() throws URISyntaxException {
        try {
            ChatWebsocketClient chatWebsocketClient = new ChatWebsocketClient(new URI(chatEndpoint + appId));
            chatWebsocketClient.connect();
            return chatWebsocketClient;
        } catch (Exception e) {
            log.error("建立 Socket 连接发生异常: ", e);
            throw e;
        }
    }
}
