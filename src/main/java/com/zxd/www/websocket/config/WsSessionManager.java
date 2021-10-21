package com.zxd.www.websocket.config;

import com.zxd.www.websocket.bean.WebSocketBean;
import com.zxd.www.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Makonike
 * @date 2021-10-10 10:17
 **/
@Slf4j
@Component
public class WsSessionManager {

    /**
     * 存放每个客户端对应的session
     */
    private static final Map<String, Map<String, WebSocketBean>> SESSION_POOL;

    static {
        // concurrent包的线程安全map
        SESSION_POOL = new ConcurrentHashMap<>();
    }

    public static void add(String key, Map<String, WebSocketBean> map) {
        SESSION_POOL.put(key, map);
    }

    /**
     * 用作管理员手动移出所有连接
     * 移出并关闭所有session
     * @param key groupId
     */
    public static boolean removeAll(String key){
        Map<String, WebSocketBean> cMap = WsSessionManager.get(key);
        if(cMap != null){
            Set<Map.Entry<String, WebSocketBean>> set = cMap.entrySet();
            try {
                for (Map.Entry<String, WebSocketBean> s : set) {
                    s.getValue().getSession().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return SESSION_POOL.remove(key) != null;
        }
        return false;
    }

    /**
     * 删除组别session
     * @param key groupId
     */
    public static boolean remove(String key){
        Map<String, WebSocketBean> remove = SESSION_POOL.remove(key);
        return remove != null;
    }

    /**
     * groupId
     * @param groupId groupId
     */
    public static Map<String, WebSocketBean> get(String groupId){
        return SESSION_POOL.get(groupId);
    }

}
