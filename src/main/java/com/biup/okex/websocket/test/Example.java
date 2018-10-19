package com.biup.okex.websocket.test;


import com.biup.okex.websocket.BuissnesWebSocketServiceImpl;
import com.biup.okex.websocket.WebSocketService;
import com.biup.okex.websocket.WebSoketClient;

/**
 * WebSocket API使用事例
 * 
 * @author okcoin
 * 
 */
public class Example {
	public static void main(String[] args) {


		String apiKey = "e7f8aba0-c002-478d-883b-9fae2d2927c0";
		String secretKey = "0C622CF60D07DCECEB5B8F230715115F";
		String url = "wss://real.okex.com:10441/websocket";
		// 订阅消息处理类,用于处理WebSocket服务返回的消息
		WebSocketService service = new BuissnesWebSocketServiceImpl();
		// WebSocket客户端
		WebSoketClient client = new WebSoketClient(url, service);
		// 启动客户端
		client.start();
		String channel="ok_sub_spot_bch_btc_ticker";
		channel="ok_sub_spot_btc_usdt_order";
		client.login(apiKey, secretKey);
		client.orderInfo(apiKey, secretKey,channel);
	}
}
