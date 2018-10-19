package com.biup.okex.Impl;

import com.alibaba.fastjson.JSONObject;
import com.biup.okex.po.OrderRes;
import com.biup.okex.service.OrderResService;
import com.biup.okex.util.MqConstant;
import com.biup.okex.util.OrderOueue;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("okexSender")
public class OkexSender implements RabbitTemplate.ReturnCallback {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OkexSender.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderResService orderResService;

    public void send(String queue, OrderRes orderRes) {
        String json = "";
        try {
            Integer id = orderResService.insert(orderRes);
            orderRes.setId(id);
            json = JSONObject.toJSONString(orderRes);
            logger.info("okex消息发送mq信息:{}", json);
            orderRes.setFlag(0);
            orderRes.setMtime(new Date());
        } catch (Exception e) {
            logger.info("okex消息入库异常:", e);
        }
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                logger.info("okex消息发送mq失败" + cause + correlationData.toString());
            } else {
                logger.info("okex消息发送mq成功");
            }
        });
        this.rabbitTemplate.convertAndSend(queue, json);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("okex消息发送mq成功" + message.toString() + "===" + i + "===" + s1 + "===" + s2);
    }
}
