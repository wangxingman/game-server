package com.game.log.rabbit;

import com.game.log.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: @
 * @Desc:
 * @Date: 下午 8:12 2019/10/29 0029
 * @Param null
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        rabbitTemplate.convertAndSend(RabbitConfig.queueName, "Hello from RabbitMQ!");
    }

}