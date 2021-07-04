package com.minhnk.batch.service;

import com.minhnk.batch.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BatchService {

    @Autowired
    private RestTemplate restTemplate;

    public List<ResponseTemplateVO> getAllPostWithComments() {

        List<ResponseTemplateVO> voList = new ArrayList<ResponseTemplateVO>();
        ResponseEntity<ResponseTemplateVO[]> responseEntity = restTemplate.getForEntity("http://localhost:5000/api/query/posts/P", ResponseTemplateVO[].class);
        ResponseTemplateVO[] responseTemplateVO = responseEntity.getBody();

        voList = Arrays.asList(responseTemplateVO);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<List<ResponseTemplateVO>> entity = new HttpEntity<List<ResponseTemplateVO>>(voList, headers);

        String result = restTemplate.exchange("http://localhost:5000/api/query/updatePosts", HttpMethod.PUT, entity, String.class).getBody();

        return voList;
    }
}
