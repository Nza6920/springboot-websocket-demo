package com.niu.socket.stomp.websocket;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Stomp 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/01/18 09:40]
 * @createTime [2021/01/18 09:40]
 */
@ConfigurationProperties(prefix = "websocket.stomp")
@Data
public class StompProperties {

    /**
     * 主机地址
     */
    private String host;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 用户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * virtualhost
     */
    private String virtualhost;
}
