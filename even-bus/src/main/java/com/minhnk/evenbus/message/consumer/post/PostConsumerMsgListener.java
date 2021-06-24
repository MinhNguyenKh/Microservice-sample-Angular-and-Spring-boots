package com.minhnk.evenbus.message.consumer.post;

import com.minhnk.evenbus.message.CustomDataMsg;
import com.minhnk.evenbus.service.EvenBusService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConsumerMsgListener {

    @Autowired
    private EvenBusService evenBusService;

    @RabbitListener(queues = PostConsumerMQConfig.POST_CONSUMER_QUEUE)
    public void listener(CustomDataMsg customMessageData){
        System.out.println("RabbitMQ received: " + customMessageData);
        evenBusService.publishMessage(customMessageData);
    }
}
