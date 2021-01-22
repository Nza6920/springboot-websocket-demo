package com.niu.websocket.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Objects;

/**
 * HTTP 工具类
 *
 * @author nza
 * @createTime 2021/1/22 15:32
 */
public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HashMap<String, String> parseQueryString(String queryString) {
        HashMap<String, String> maps = Maps.newHashMap();

        if (StringUtils.isEmpty(queryString)) {
            return maps;
        }

        String[] kvs = queryString.split("&");
        String[] kvItem;
        for (String item : kvs) {
            kvItem = item.split("=");
            if (kvItem.length == 2) {
                maps.put(kvItem[0], kvItem[1]);
            }
        }

        return maps;
    }

}
