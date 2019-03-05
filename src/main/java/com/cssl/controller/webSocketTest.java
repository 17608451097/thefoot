package com.cssl.controller;


import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;



//sid为页面的userid
@ServerEndpoint("/webSocket/{sid}")
@Component
public class webSocketTest{
    private static int onlineCount = 0;
    
    //用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<webSocketTest> webSocketSet = new CopyOnWriteArraySet<webSocketTest>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";
    
//成功的方法
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.sid=sid;
        this.session = session;
        System.out.println(sid);
        webSocketSet.add(this);     //加入set中
        try {
            sendMessage("连接成功...");
        } catch (IOException e) {
        	System.out.println("websocket IO异常");
        }
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除

    }

  //收到客服端调用的方法
    //message是客户端发送过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
    	System.out.println("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (webSocketTest item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
    
//服务器的主动推送
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

//群发消息
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        for (webSocketTest item : webSocketSet) {
            try {
                //推送这个指定的id
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
    public static webSocketTest getWebSocket(String sid) {
        if (webSocketSet == null || webSocketSet.size() <= 0) {
            return null;
        }
        for (webSocketTest item : webSocketSet) {
            if (sid.equals(item.sid)) {
                return item;
            }
        }
        return null;
    }
}
	 