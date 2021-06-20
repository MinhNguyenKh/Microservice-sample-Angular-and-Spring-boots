package com.minhnk.comment.service;

import com.minhnk.comment.VO.SendDataVO;
import com.minhnk.comment.constant.ApiUrl;
import com.minhnk.comment.entity.Comment;
import com.minhnk.comment.message.CommentDataMsg;
import com.minhnk.comment.message.CommentMQConfig;
import com.minhnk.comment.repository.CommentRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

    public String sendDataToEvenBus(Comment savedComment){
        SendDataVO sendDataVO = new SendDataVO();
        sendDataVO.setType("Comment created");
        sendDataVO.setId(savedComment.getId());
        sendDataVO.setContent(savedComment.getContent());
        sendDataVO.setPostId(savedComment.getPostId());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SendDataVO> entity = new HttpEntity<SendDataVO>(sendDataVO, headers);

        //Sending message to even-bus by RabbitMQ
        CommentDataMsg commentDataMsg = new CommentDataMsg();
        commentDataMsg.setId(savedComment.getId());
        commentDataMsg.setContent(savedComment.getContent());
        commentDataMsg.setPostId(savedComment.getPostId());
        this.publishMessage(commentDataMsg);

        String result = restTemplate.exchange(ApiUrl.EVEN_BUS_API_URL, HttpMethod.POST, entity, String.class).getBody();
        System.out.println(result);
        return result;
    }

    public String getMessageFromEvenBus(String message) {
        System.out.println(message);
        return message;
    }

    public String publishMessage(CommentDataMsg commentDataMsg){
        rabbitTemplate.convertAndSend(CommentMQConfig.TOPIC_EXCHANGE, CommentMQConfig.ROUTING_KEY, commentDataMsg);
        return "Message published!";
    }
}
