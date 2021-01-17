package com.niu.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 支持 websocket 调用此方法
     */
    @MessageMapping("/send")
    @SendTo("/topic")
    public String handleTopic(String message) {
        return message;
    }

    /**
     * 自动降级将调用此方法
     */
    @GetMapping("/send")
    public void topicMsgReply(@RequestParam String msg) {
        simpMessagingTemplate.convertAndSend("/topic", msg);
    }

    /**
     * 群发消息
     */
    @MessageMapping("/send2All")
    @GetMapping("/send2All")
    public void handleSend2All(String message) {
        simpMessagingTemplate.convertAndSend("/send2All", message);
    }

    /**
     * 点对点发送
     */
    @MessageMapping("/send2User")
    public void handleSend2User(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String toUserId = message.get("toUserId");
        String msg = "来自: " + fromUserId + " 的消息: " + message.get("msg");

        simpMessagingTemplate.convertAndSendToUser(toUserId, "/topic", msg);
    }
}
