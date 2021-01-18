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
     * 需要手动创建Exchange
     * 自动创建队列, 队列不会持久化
     * <p>
     * /exchange/<exchangeName>/<queueName>
     */
    @MessageMapping("/send2User/exchange")
    public void handleSend2UserByExchange(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String toUserId = message.get("toUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String exchange = "send2User";
        String destination = "/exchange/" + exchange + "/user" + toUserId;

        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    /**
     * 通过 Exchange 群发消息
     * 需要手动创建Exchange
     * 自动创建队列, 队列不会持久化
     * <p>
     * /exchange/<exchangeName>/<queueName>
     */
    @MessageMapping("/send2All/exchange")
    public void handleSendAllByExchange(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String exchange = "all";
        String destination = "/exchange/" + exchange + "/user";

        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    /**
     * 通过 Queue 点对点发送消息
     * 使用默认的Exchange
     * 自动创建队列, 并把队列持久化
     * <p>
     * 多个订阅会轮询发送
     * <p>
     * /queue/<queuename>
     */
    @MessageMapping("/send2User/queue")
    public void handleSend2UserByQueue(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String toUserId = message.get("toUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String destination = "/queue/user" + toUserId;

        simpMessagingTemplate.convertAndSend(destination, msg);
    }


    /**
     * 通过 Queue 点对点发送消息
     * 使用默认的 Exchange 发送
     * 需要手动创建队列
     * <p>
     * 多个订阅会轮询发送
     *
     * <p>
     * /amq/queue/<queuename>
     */
    @MessageMapping("/send2User/amqQueue")
    public void handleSend2UserByAmpQueue(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String destination = "/amq/queue/amqQueue";

        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    /**
     * 通过 topic routingKey 点对点发送消息
     * 使用默认的 Exchange 发送
     * 创建出自动删除的、非持久的 queue
     * 根据 topicName(routingKey) 的规则匹配队列
     *
     * <p>
     * /topic/<topicName>
     */
    @MessageMapping("/send2User/topic")
    public void handleSend2UserByTopic(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String toUserId = message.get("toUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String destination = "/topic/user" + toUserId;

        simpMessagingTemplate.convertAndSend(destination, msg);
    }

    /**
     * 通过 topic routingKey 点对点发送消息
     * 使用默认的 Exchange 发送
     * 根据 topicName(routingKey) 的规则匹配队列
     * <p>
     * /topic/<topicName>
     */
    @MessageMapping("/send2All/topic")
    public void handleSend2AllByTopic(Map<String, String> message) {

        String fromUserId = message.get("fromUserId");
        String msg = "来自 [" + fromUserId + "] 的消息: " + message.get("msg");

        String destination = "/topic/chat";

        simpMessagingTemplate.convertAndSend(destination, msg);
    }
}
