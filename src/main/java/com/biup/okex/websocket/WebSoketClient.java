package com.biup.okex.websocket;


import com.biup.okex.websocket.WebSocketBase;
import com.biup.okex.websocket.WebSocketService;

/**
 * 通过继承WebSocketBase创建WebSocket客户端
 * @author okcoin
 *
 */
public class WebSoketClient extends WebSocketBase {
	public WebSoketClient(String url,WebSocketService service){
		super(url,service);
	}
}
