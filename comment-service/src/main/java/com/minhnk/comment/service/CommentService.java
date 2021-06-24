package com.minhnk.comment.service;

import com.minhnk.comment.VO.SendDataVO;
import com.minhnk.comment.entity.Comment;
import com.minhnk.comment.message.CustomDataMsg;
import com.minhnk.comment.message.producer.CommentProducerMQConfig;
import com.minhnk.comment.repository.CommentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Comment createComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        this.sendDataToEvenBus(savedComment);
        return savedComment;
    }

    public List<Comment> findCommentByPostId(Long postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    public void sendDataToEvenBus(Comment savedComment){
        SendDataVO sendDataVO = new SendDataVO();
        sendDataVO.setType("Comment created");
        sendDataVO.setId(savedComment.getId());
        sendDataVO.setContent(savedComment.getContent());
        sendDataVO.setPostId(savedComment.getPostId());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SendDataVO> entity = new HttpEntity<SendDataVO>(sendDataVO, headers);

        //Sending message to even-bus by RabbitMQ
        CustomDataMsg customDataMsg = new CustomDataMsg();
        customDataMsg.setCommentId(savedComment.getId());
        customDataMsg.setContent(savedComment.getContent());
        customDataMsg.setPostId(savedComment.getPostId());
        customDataMsg.setType("Comment");
        this.publishMessage(customDataMsg);

//        String result = restTemplate.exchange(ApiUrl.EVEN_BUS_API_URL, HttpMethod.POST, entity, String.class).getBody();
//        System.out.println(result);
//        return result;
    }

    public String getMessageFromEvenBus(String message) {
        System.out.println(message);
        return message;
    }

    public String publishMessage(CustomDataMsg customDataMsg){
        rabbitTemplate.convertAndSend(CommentProducerMQConfig.COMMENT_PRODUCER_TOPIC_EXCHANGE, CommentProducerMQConfig.COMMENT_PRODUCER_ROUTING_KEY, customDataMsg);
        return "Message published!";
    }
}
