package com.niu.websocket.controller;

import com.niu.websocket.config.StompProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * WebSocket 控制器
 *
 * @author [nza]
 * @version 1.0 2021/1/17
 * @createTime 14:00
 */
@RestController
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private StompProperties stompProperties;

    /**
     * 通过 Exchange 点对点发送消息
     */
    @MessageMapping("/send2User/exchange")
    public void handleSend2UserByExchange(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String toUserId = message.get("toUserId");
        String msg = "来自: " + fromUserId + " 的消息: " + message.get("msg");

        String exchange = "send2User";
        String destination = "/exchange/" + exchange + "/user" + toUserId;

        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    /**
     * 通过 Exchange 群发消息
     */
    @MessageMapping("/send2All/exchange")
    public void handleSendAllByExchange(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String msg = "来自: " + fromUserId + " 的消息: " + message.get("msg");

        String exchange = "all";
        String destination = "/exchange/" + exchange + "/user";

        simpMessagingTemplate.convertAndSend(destination, msg);
    }
}
