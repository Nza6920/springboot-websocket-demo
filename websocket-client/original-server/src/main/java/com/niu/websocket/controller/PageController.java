package com.niu.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 视图控制器
 *
 * @version 1.0 [2021/01/22 15:39]
 * @author [nza]
 * @createTime [2021/01/22 15:39]
 */
@Controller
public class PageController {


    @GetMapping("/show")
    public String show() {
        return "/show.html";
    }
}
