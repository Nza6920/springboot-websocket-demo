package com.niu.websocket.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String msgReply(@RequestParam String msg) {
        simpMessagingTemplate.convertAndSend("/topic", msg);
        return msg;
    }
}
