package com.biup.okex;

import com.biup.okex.websocket.WebSocketClientHandler;
import com.biup.okex.websocket.WebSocketService;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import org.junit.Test;

import java.net.URI;
import java.util.Timer;

public class WebSocketClientTest {
    static final String url = "ws://localhost:8090/appws";

    private WebSocketService service = null;

    private EventLoopGroup group = null;
    private Bootstrap bootstrap = null;
    private Channel channel = null;
    private ChannelFuture future = null;


    @Test
    public void connect() {
        try {
            final URI uri = new URI(url);
            group = new NioEventLoopGroup(1);
            bootstrap = new Bootstrap();
            final SslContext sslCtx = SslContext.newClientContext();
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
                                    new HttpObjectAggregator(8192));
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

            WebSocketFrame frame = new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[]{8, 1, 8, 1}));
            channel.writeAndFlush(frame);
            System.out.println("over");

        } catch (Exception e) {

            group.shutdownGracefully();

        }
    }
}
