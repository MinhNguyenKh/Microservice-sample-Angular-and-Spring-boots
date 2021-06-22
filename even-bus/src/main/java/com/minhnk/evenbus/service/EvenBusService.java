package com.minhnk.evenbus.service;

import com.minhnk.evenbus.VO.ResponseTemplateVO;
import com.minhnk.evenbus.constant.ApiUrl;
import com.minhnk.evenbus.message.EvenBusDataMsg;
import com.minhnk.evenbus.message.comment.CommentMQConfig;
import com.minhnk.evenbus.message.post.PostMQConfig;
import com.minhnk.evenbus.message.query.QueryMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EvenBusService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String getEvent(ResponseTemplateVO responseTemplateVO) {
        String message = "";
        String url = "";
        String queryUrl = "";
        String queryMessageUrl = "";
        if(responseTemplateVO.getType().equalsIgnoreCase("Post created")){
            message = responseTemplateVO.getType() + " - id: " + responseTemplateVO.getId()
                    + ", title: " + responseTemplateVO.getTitle();
            url = ApiUrl.POST_SERVICE_API_URL + "events";
            queryUrl = ApiUrl.QUERY_SERVICE_API_URL + "posts";
            queryMessageUrl = ApiUrl.QUERY_SERVICE_API_URL + "events";
        }else if(responseTemplateVO.getType().equalsIgnoreCase("Comment created")){
            message = responseTemplateVO.getType() + " - id: " + responseTemplateVO.getId()
                    + ", content: " + responseTemplateVO.getContent() + ", postId: " + responseTemplateVO.getPostId();
            url = ApiUrl.COMMENT_SERVICE_API_URL + "events";
            queryUrl = ApiUrl.QUERY_SERVICE_API_URL + "posts/" + responseTemplateVO.getPostId() + "/comments";
            queryMessageUrl = ApiUrl.QUERY_SERVICE_API_URL + "events";
        }
//        this.sendMessage(message, url);
//        this.sendMessage(message, queryMessageUrl);
//        this.sendDataToQuery(queryUrl, responseTemplateVO);
        return message;
    }

    public String sendMessage(String message, String url) {
        String result = restTemplate.postForObject(url, message, String.class);
        return result;
    }

    public String sendDataToQuery(String queryUrl, ResponseTemplateVO responseTemplateVO){
        String result = restTemplate.postForObject(queryUrl, responseTemplateVO, String.class);
        return result;
    }

    public String publishMessage(EvenBusDataMsg evenBusDataMsg, String destination){
        if("Post".equalsIgnoreCase(destination)){
            rabbitTemplate.convertAndSend(PostMQConfig.TOPIC_EXCHANGE, PostMQConfig.ROUTING_KEY, evenBusDataMsg);
        }else{
            rabbitTemplate.convertAndSend(CommentMQConfig.TOPIC_EXCHANGE, CommentMQConfig.ROUTING_KEY, evenBusDataMsg);
        }
        rabbitTemplate.convertAndSend(QueryMQConfig.TOPIC_EXCHANGE, QueryMQConfig.ROUTING_KEY, evenBusDataMsg);
        return "Message published!";
    }

}
