package com.niu.websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author Zian.Niu
 */
@RestController
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendToUser")
    public void sendToUser(Map<String, String> params) {
        String fromUserId = params.get("fromUserId");
        String toUserId = params.get("toUserId");
        String msg = "来自" + fromUserId + "消息:" + params.get("msg");
        String destination = "/queue/user_" + toUserId;
        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    @MessageMapping("/sendToAll")
    public void sendToAll(String msg) {
        simpMessagingTemplate.convertAndSend("/topic/chat", msg);
    }
}
