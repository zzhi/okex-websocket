# okex-websocket

由于交易所对部分接口有频次限制，为了突破接口请求限制，交易所提供了websocket接口，本项目在官方提供的方法上做了修改
集成了数据库和消息队列，可按需删减

okex 订单订阅，websocket方式

1 ，main 方法
 //你的秘钥
accessKey = platInfo.getAccessKey();
secretKey = platInfo.getSecretKey();

2， BuissnesWebSocketServiceImpl.onReceive方法

接收的订单信息

3，WebSocketBase 订单订阅及ping/pong
