package com.niu.socket.stomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * socket 控制器
 */
@RestController
public class WebsocketController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/sendToUser")
    public void sendToUserByTemplate(Map<String, String> params) {
        String fromUserId = params.get("fromUserId");
        String toUserId = params.get("toUserId");
        String msg = "来自" + fromUserId + "消息:" + params.get("msg");

        String destination = "/queue/user_" + toUserId;
        template.convertAndSend(destination, msg);
    }

    @GetMapping("/sendToAllByTemplate")
    @MessageMapping("/sendToAllByTemplate")
    public void sendToAllByTemplate(@RequestParam String msg) {
        template.convertAndSend("/topic/chat", msg);
    }
}
