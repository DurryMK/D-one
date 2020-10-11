package spm.app.Service.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSocketUtil {
    @Autowired
    private WebSocketService wss;

    /**
     * 推送消息到客户端
     */
    public void sendMag(String key, String msg) {
        //设置要发送的目标客户端
        wss.setCurrentSession(key);
        //发送消息
        wss.onMessage(msg);
    }
}
