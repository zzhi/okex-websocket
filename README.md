# okex-websocket
okex 订单订阅，websocket方式

1 ，main 方法
 //你的秘钥
accessKey = platInfo.getAccessKey();
secretKey = platInfo.getSecretKey();

2， BuissnesWebSocketServiceImpl.onReceive方法

接收的订单信息

3，WebSocketBase 订单订阅及ping/pong
