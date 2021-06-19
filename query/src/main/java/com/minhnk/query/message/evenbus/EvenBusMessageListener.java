package com.minhnk.query.message.evenbus;

import com.minhnk.query.service.QueryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvenBusMessageListener {

    @Autowired
    private QueryService queryService;

    @RabbitListener(queues = EvenBusMQConfig.QUEUE)
    public void listener(CustomMessageData customMessageData){
        System.out.println(customMessageData);
        //evenBusService.publishMessage(postDataMsg);
    }
}
