/*

package com.biup.okex.listenner;

import com.alibaba.fastjson.JSONObject;
import com.biup.okex.util.OrderOueue;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

import java.io.IOException;

@Component
@RabbitListener(queues = OrderOueue.OKEX)
public class BinanceHandle {
    private static final Logger logger = LoggerFactory.getLogger(BinanceHandle.class);

    private static final  String binaryKey="binary";
    private static final  String channelKey="channel";
    private static final String dataKey="data";
    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        logger.info("okex接收消息:{}", msg);

       try {
           JSONObject jsonObject = JSONObject.parseObject(msg);
           if(jsonObject.containsKey(binaryKey)&&jsonObject.containsKey(channelKey)&&jsonObject.containsKey(dataKey))
           {
               Integer binary = jsonObject.getInteger(binaryKey);
               String channelVal = jsonObject.getString(channelKey);
               JSONObject data = jsonObject.getJSONObject(dataKey);
               if(binary!=null&& !Strings.isNullOrEmpty(channelVal)&&data!=null)
               {

               }
           }
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了
            //否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            logger.info("okex接收消息成功");
        } catch (IOException e) {
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            logger.info("okex接收消息失败:{}", e.getMessage());
        }

    }
}
*/
