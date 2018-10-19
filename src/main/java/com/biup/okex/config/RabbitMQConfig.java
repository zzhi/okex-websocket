package com.biup.okex.config;


import com.biup.okex.util.MqConstant;
import com.biup.okex.util.OrderOueue;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue okExSyncCreateOrdersQueue() {
        return new Queue(MqConstant.QUEUE_OKEX_CREATE);
    }
    @Bean
    public Queue okExSyncCancelOrdersQueue() {
        return new Queue(MqConstant.QUEUE_OKEX_CANCEL);
    }

    @Bean
    public MessageConverter messageConverter() {
        SimpleMessageConverter simpleMessageConverter = new SimpleMessageConverter();
        simpleMessageConverter.setCreateMessageIds(true);
        return simpleMessageConverter;
    }

    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate(MessageConverter messageConverter, ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }


}
