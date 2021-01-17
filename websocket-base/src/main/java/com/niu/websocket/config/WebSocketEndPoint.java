package com.niu.websocket.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * WebSocket 生命周期处理器
 * <p>
 * ws://ip:7016/websocket/{userid}
 *
 * @author [nza]
 * @version 1.0 [2021/01/15 14:58]
 * @createTime [2021/01/15 14:58]
 */
@ServerEndpoint(value = "/websocket/{userId}")
@Component
@Slf4j
public class WebSocketEndPoint {

    /**
     * 建立 webSocket 连接建立后触发
     *
     * @param session 当前会话
     * @param userId  url参数
     * @author nza
     * @createTime 2021/1/15 15:01
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        log.info("--------- webSocket 连接建立中 ---------");
        SessionPool.add(userId, session);
        log.info("--------- webSocket 连接建立成功 ---------");
    }

    /**
     * 连接关闭时触发
     *
     * @param session 会话
     * @author nza
     * @createTime 2021/1/15 15:09
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        log.info("--------- webSocket 连接关闭中 ---------");
        SessionPool.close(session.getId());
        session.close();
        log.info("--------- webSocket 连接关闭成功 ---------");
    }

    /**
     * 发送消息时触发
     *
     * @param message 消息
     * @param session 会话
     * @author nza
     * @createTime 2021/1/15 15:20
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("--------- webSocket 消息发送中 ---------");

        // 判断是否是心跳包检测
        if (message.equalsIgnoreCase("ping")) {
            try {
                HashMap<String, Object> res = new HashMap<>();
                res.put("type", "pong");
                session.getBasicRemote().sendText(JSON.toJSONString(res));
                log.info("应答客户端心跳");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        Map<String, Object> data = JSON.parseObject(message, Map.class);
        // 判断是否是群发消息
        if (data.containsKey("all")) {
            SessionPool.sendMessage((String) data.get("msg"));
        } else {
            SessionPool.sendMessage(data);
        }

        log.info("--------- webSocket 消息发送成功 ---------");
    }
}
