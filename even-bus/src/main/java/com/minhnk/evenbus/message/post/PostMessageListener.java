package com.minhnk.evenbus.message.post;

import com.minhnk.evenbus.message.EvenBusMQConfig;
import com.minhnk.evenbus.service.EvenBusService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMessageListener {

    @Autowired
    private EvenBusService evenBusService;

    @RabbitListener(queues = PostMQConfig.QUEUE)
    public void listener(PostDataMsg postDataMsg){
        System.out.println(postDataMsg);
        //evenBusService.publishMessage(postDataMsg);
    }
}
