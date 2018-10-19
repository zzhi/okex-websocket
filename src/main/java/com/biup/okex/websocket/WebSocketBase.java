package com.biup.okex.websocket;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;

public abstract class WebSocketBase {


    private static final Logger log = LoggerFactory.getLogger(WebSocketBase.class);
    private WebSocketService service = null;
    private Timer timerTask = null;
    private MoniterTask moniter = null;
    private EventLoopGroup group = null;
    private Bootstrap bootstrap = null;
    private Channel channel = null;
    private String url = null;
    private ChannelFuture future = null;
    private boolean isAlive = false;
    private String apiKey;
    private String secretKey;
    /**
     * 国内站siteFlag=0,国际站siteFlag=1
     */
    private int siteFlag = 0;
    private Set<String> subscribChannel = new HashSet<String>();

    public WebSocketBase(String url, WebSocketService serivce) {
        this.url = url;
        this.service = serivce;
    }

    public void start() {
        if (url == null) {
            log.info("地址不能为空");
            return;
        }
        if (service == null) {
            log.info("WebSocketService不能为空");
            return;
        }
        moniter = new MoniterTask(this);
        this.connect();
        timerTask = new Timer();
        timerTask.schedule(moniter, 1000, 5000);
    }

    public void setStatus(boolean flag) {
        this.isAlive = flag;
    }

    private void addChannel(String channel) {
        if (channel == null) {
            return;
        }
        subscribChannel.add(channel);
    }

    /**
     * 订单信息
     *
     * @param apiKey
     * @param secretKey
     */
    public void orderInfo(String apiKey, String secretKey, String channel) {
        log.debug("apiKey=" + apiKey + ", secretKey=" + secretKey);
        StringBuilder preStr = new StringBuilder("api_key=");
        preStr.append(apiKey).append("&secret_key=").append(secretKey);
        String signStr = MD5Util.getMD5String(preStr.toString());
        StringBuilder tradeStr = new StringBuilder(
                "{'event':'addChannel','channel':'" + channel + "','parameters':{'api_key':'")
                .append(apiKey).append("','sign':'").append(signStr)
                .append("'}}");
        log.info(tradeStr.toString());
        addChannel(channel);
        this.sendMessage(tradeStr.toString());
    }


    /**
     * 登录
     *
     * @param apiKey
     * @param secretKey
     */
    public void login(String apiKey, String secretKey) {
        log.debug("apiKey=" + apiKey + ", secretKey=" + secretKey);
        this.apiKey=apiKey;
        this.secretKey=secretKey;
        StringBuilder preStr = new StringBuilder("api_key=");
        preStr.append(apiKey).append("&secret_key=").append(secretKey);
        String signStr = MD5Util.getMD5String(preStr.toString());
        StringBuilder tradeStr = new StringBuilder(
                "{'event':'login','parameters':{'api_key':'")
                .append(apiKey).append("','sign':'").append(signStr)
                .append("'}}");
        log.info(tradeStr.toString());
        this.sendMessage(tradeStr.toString());
    }
    private void connect() {
        try {
            final URI uri = new URI(url);
            if (uri == null) {
                return;
            }
            if (uri.getHost().contains("com")) {
                siteFlag = 1;
            }
            group = new NioEventLoopGroup(1);
            bootstrap = new Bootstrap();
            final SslContext sslCtx = SslContext.newClientContext();
            final WebSocketClientHandler handler = new WebSocketClientHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(uri,
                            WebSocketVersion.V13, null, false,
                            new DefaultHttpHeaders(), Integer.MAX_VALUE),
                    service, moniter);
            bootstrap.group(group).option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(),
                                        uri.getHost(), uri.getPort()));
                            }
                            p.addLast(new HttpClientCodec(),
                                    new HttpObjectAggregator(8192), handler);
                        }
                    });

            future = bootstrap.connect(uri.getHost(), uri.getPort());
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(final ChannelFuture future)
                        throws Exception {
                }
            });
            channel = future.sync().channel();
            handler.handshakeFuture().sync();
            this.setStatus(true);
        } catch (Exception e) {
            log.info("WebSocketClient start error ", e);
            group.shutdownGracefully();
            this.setStatus(false);
        }
    }

    private void sendMessage(String message) {
        if (!isAlive) {
            log.info("WebSocket is not Alive addChannel error");
        }
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    public void sentPing() {
        String dataMsg = "{'event':'ping'}";
        this.sendMessage(dataMsg);
    }

    public void reConnect() {
        log.info("重连:{};{}",this.apiKey,this.secretKey);
        try {
            this.group.shutdownGracefully();
            this.group = null;
            this.connect();
            this.login(this.apiKey,this.secretKey);
            if (future.isSuccess()) {
                this.setStatus(true);
                this.sentPing();
                Iterator<String> it = subscribChannel.iterator();
                while (it.hasNext()) {
                    String channel = it.next();
                    log.info("重连channel:{}",channel);
                    this.orderInfo(this.apiKey,this.secretKey,channel);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

class MoniterTask extends TimerTask {


    private static final Logger log = LoggerFactory.getLogger(MoniterTask.class);
    private long startTime = System.currentTimeMillis();
    private int checkTime = 5000;
    private WebSocketBase client = null;

    public void updateTime() {
        //log.info("startTime is update");
        startTime = System.currentTimeMillis();
    }

    public MoniterTask(WebSocketBase client) {
        this.client = client;
        log.info("TimerTask is starting.... ");
    }

    @Override
    public void run() {
        if (System.currentTimeMillis() - startTime > checkTime) {
            client.setStatus(false);
            log.info("Moniter reconnect....... ");
            client.reConnect();
        }
        client.sentPing();
        //log.info("Moniter ping data sent.... ");
    }

}
