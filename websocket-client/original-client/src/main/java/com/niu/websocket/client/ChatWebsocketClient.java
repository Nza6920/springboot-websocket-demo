package com.niu.websocket.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * 客户端Socket实现
 *
 * @author [nza]
 * @version 1.0 [2021/01/22 15:06]
 * @createTime [2021/01/22 15:06]
 */
@Slf4j
public class ChatWebsocketClient extends WebSocketClient {

    public ChatWebsocketClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("------------------------------");
        log.info("客户端连接成功");
        log.info("------------------------------");
    }

    @Override
    public void onMessage(String s) {
        log.info("------------------------------");
        log.info("接收到服务端的数据: {}", s);
        log.info("------------------------------");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("------------------------------");
        log.info("客户端连接关闭");
        log.info("状态码: {}", code);
        log.info("附加信息: {}", reason);
        log.info("是否是服务端发起关闭: {}", remote);
        log.info("------------------------------");
    }

    @Override
    public void onError(Exception e) {
        log.info("------------------------------");
        log.info("客户端发生异常, 异常信息: ", e);
        log.info("------------------------------");
    }
}
