package com.niu.websocket.config;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;

/**
 * Session 存储器
 *
 * @author [nza]
 * @version 1.0 [2021/01/15 15:03]
 * @createTime [2021/01/15 15:03]
 */
public class SessionPool {


    /**
     * 会话池 <ID, session>
     */
    private static final Map<String, Session> POOL = Maps.newConcurrentMap();

    /**
     * 关闭连接
     *
     * @param sessionId 会话ID
     * @author nza
     * @createTime 2021/1/15 15:06
     */
    public static void close(String sessionId) throws IOException {
        for (String id : POOL.keySet()) {
            Session session = POOL.get(id);
            if (StringUtils.equals(session.getId(), sessionId)) {
                POOL.remove(id);
                break;
            }
        }
    }

    /**
     * 群发发送消息
     *
     * @param message 消息
     * @author nza
     * @createTime 2021/1/15 15:08
     */
    public static void sendMessage(String message) {
        for (String id : POOL.keySet()) {
            POOL.get(id).getAsyncRemote().sendText(message);
        }
    }

    /**
     * 发送消息
     *
     * @param sessionId 会话ID
     * @param message   消息
     * @author nza
     * @createTime 2021/1/15 15:08
     */
    public static void sendMessage(String sessionId, String message) {
        for (String id : POOL.keySet()) {
            Session session = POOL.get(id);
            if (StringUtils.equals(sessionId, session.getId())) {
                session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    /**
     * 添加会话
     *
     * @param id      ID
     * @param session 会话
     * @author nza
     * @createTime 2021/1/15 15:10
     */
    public static void add(String id, Session session) {
        POOL.put(id, session);
    }

    /**
     * 指定发送消息
     *
     * @param data 发送数据
     * @author nza
     * @createTime 2021/1/15 16:19
     */
    public static void sendMessage(Map<String, Object> data) {
        String toUserId = data.get("toUserId").toString();
        String msg = data.get("msg").toString();
        String fromUserId = data.get("fromUserId").toString();

        msg = "来自" + fromUserId + "的消息: " + msg;

        Session session = POOL.get(toUserId);
        if (session != null) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}
