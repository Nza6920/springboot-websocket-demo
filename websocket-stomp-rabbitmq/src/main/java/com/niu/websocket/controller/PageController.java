package com.niu.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面控制器
 *
 * @author [nza]
 * @version 1.0 [2021/01/18 15:11]
 * @createTime [2021/01/18 15:11]
 */
@Controller
public class PageController {

    /**
     * 首页
     *
     * @return 页面
     */
    @GetMapping("/show")
    public String show() {
        return "show.html";
    }
}
