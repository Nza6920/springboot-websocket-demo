package com.niu.websocket.security.controller;

import com.niu.websocket.security.entity.CommonResult;
import com.niu.websocket.security.services.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsAdminService adminService;

    @PostMapping(value = "/login")
    public CommonResult<Map<String, String>> login(@RequestBody Map<String, Object> params) {

        String username = params.get("username").toString();

        String password = params.get("password").toString();

        String token = adminService.login(username, password);

        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return CommonResult.success(tokenMap);
    }

    @GetMapping(value = "/refreshToken")
    public CommonResult<Map<String, String>> refreshToken(HttpServletRequest request) {

        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);

        if (refreshToken == null) {
            return CommonResult.failed("token已经过期");
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @PostMapping(value = "/logout")
    public CommonResult<String> logout() {
        return CommonResult.success(null);
    }
}
