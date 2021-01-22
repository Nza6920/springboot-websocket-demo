package com.niu.websocket.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.niu.websocket.client.ChatWebsocketClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 发送消息控制器
 *
 * @author [nza]
 * @version 1.0 [2021/01/22 15:24]
 * @createTime [2021/01/22 15:24]
 */
@RestController
@Slf4j
public class ChatController {

    @Autowired
    private ChatWebsocketClient chatWebsocketClient;

    @ResponseBody
    @GetMapping("/send")
    public void sendMessage(@RequestParam Map<String, Object> params) {

        Map<String, Object> maps = Maps.newHashMapWithExpectedSize(3);
        maps.put("fromUserId", 100);
        maps.put("toUserId", params.get("userId").toString());
        maps.put("msg", params.get("msg").toString());
        String json = JSON.toJSONString(maps);

        // 发送消息
        chatWebsocketClient.send(json);
    }
}
