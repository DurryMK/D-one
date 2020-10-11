package spm.app.Service.websocket;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/doneWs/{token}")
@Service
public class WebSocketService {

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();
    /**
     * 当前的目标客户端
     */
    private static Session currentSession = null;

    /**
     * 群发目标
     */
    private static HashSet<Session> targets = new HashSet<>();

    /**
     * 设置目标Session
     */
    public void setCurrentSession(String key) {
        //从Session容器中取出目标Session
        Session session = clients.get(key);
        if (session != null) {
            currentSession = session;
        } else {
            System.out.println("目标客户端已经断开连接");
        }
    }

    /**
     * 设置群发目标
     */
    public void setTargets(HashSet<String> keys) {
        //清空群发列表
        targets.clear();
        //从Session容器中取出目标Session
        Session session = null;
        for (String key : keys) {
            session = clients.get(key);
            //添加到群发列表
            if (session != null) {
                targets.add(session);
            }
        }
    }

    @OnOpen()
    public void onOpen(Session session, @PathParam("token") String token) {
        //检验该客户端是否已经在线

        //保存到session容器中
        clients.put(token, session);
        hit(0, session.getId(), token);
    }

    /**
     * 客户端关闭
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session, @PathParam("token") String token) {
        if (clients.containsKey(token)) {
            //用户下线  删除session
            clients.remove(token);
        }
        hit(1,session.getId(), token);
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        //currentSession.getAsyncRemote().sendText(message);
        System.out.println("客户端发来的消息" + message);
    }

    /**
     * 群发消息
     *
     * @param message 消息内容
     */
    private void sendAll(String message) {
    }

    /**
     * 后台提示符
     */
    private void hit(int flag, String id, String token) {
        System.out.println("-------------------------------");
        if (flag == 0) {
            System.out.println("一个客户端连接到服务器:");

        } else {
            System.out.println("一个客户端下线:");
        }
        System.out.println("Session ID:" + id);
        System.out.println("token:" + token);
        System.out.println("目前有" + clients.size() + "个客户端在线");
        System.out.println("-------------------------------");

    }
}
