package com.minhnk.comment.message.evenbus;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EvenBusMessageListener {

    @RabbitListener(queues = EvenBusMQConfig.QUEUE)
    public void listener(EvenBusMessageData customMessageData){
        System.out.println("Comment created: " + customMessageData);
    }
}
