package com.minhnk.evenbus.message.comment;

import com.minhnk.evenbus.message.post.PostDataMsg;
import com.minhnk.evenbus.message.post.PostMQConfig;
import com.minhnk.evenbus.service.EvenBusService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMessageListener {

    @Autowired
    private EvenBusService evenBusService;

    @RabbitListener(queues = CommentMQConfig.QUEUE)
    public void listener(CommentDataMsg commentDataMsg){
        System.out.println(commentDataMsg);
        //evenBusService.publishMessage(postDataMsg);
    }
}
