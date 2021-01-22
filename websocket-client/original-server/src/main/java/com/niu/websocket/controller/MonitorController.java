package com.niu.websocket.controller;

import com.niu.websocket.websocket.SessionPool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 *
 */
@Controller
public class MonitorController {

    @ResponseBody
    @GetMapping("/sendMessage")
    public static String sendMessage(@RequestParam Map<String, Object> params) {
        SessionPool.sendMessage(params);
        return "发送成功";
    }
}
