package com.minhnk.evenbus.service;

import com.minhnk.evenbus.VO.ResponseTemplateVO;
import com.minhnk.evenbus.constant.ApiUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EvenBusService {

    @Autowired
    private RestTemplate restTemplate;

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
        this.sendMessage(message, url);
        this.sendMessage(message, queryMessageUrl);
        this.sendDataToQuery(queryUrl, responseTemplateVO);
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

}
