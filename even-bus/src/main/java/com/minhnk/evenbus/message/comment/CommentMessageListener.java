package com.minhnk.evenbus.message.comment;

import com.minhnk.evenbus.message.EvenBusDataMsg;
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
        System.out.println("RabbitMQ: " + commentDataMsg);
        EvenBusDataMsg evenBusDataMsg = new EvenBusDataMsg();
        evenBusDataMsg.setCommentId(commentDataMsg.getId());
        evenBusDataMsg.setContent(commentDataMsg.getContent());
        evenBusDataMsg.setPostId(commentDataMsg.getPostId());
        evenBusDataMsg.setType("Comment");
        evenBusService.publishMessage(evenBusDataMsg, evenBusDataMsg.getType());
    }
}
