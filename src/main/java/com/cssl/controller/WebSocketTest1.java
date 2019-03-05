package com.cssl.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;


@ServerEndpoint("/zx")
@Component
public class WebSocketTest1 {
	//静态变量，用来记录当前在线连接数
	private static int onlineCount = 0;
	//静态变量，用来记录用户传输的数据
	private static String text ="";

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<WebSocketTest1> webSocketSet = new CopyOnWriteArraySet<WebSocketTest1>();
	
	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		if(getOnlineCount()==2) {
			try {
				sendMessage(text);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		deltext();    //清除数据
	}


	@OnMessage
	public void onMessage(String message, Session session) {
		if(getOnlineCount()==1) {
			addtext(message);
		}
		
		//群发消息
		for(WebSocketTest1 item: webSocketSet){
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException{
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketTest1.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketTest1.onlineCount--;
	}
	
	
	public static synchronized String gettext() {
		return text;
	}

	public static synchronized void addtext(String mes) {
		WebSocketTest1.text+=mes;
	}

	public static synchronized void deltext() {
		WebSocketTest1.text="";
	}
}
