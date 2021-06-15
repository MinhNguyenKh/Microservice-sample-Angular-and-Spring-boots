package com.minhnk.post.service;

import com.minhnk.post.VO.Comment;
import com.minhnk.post.VO.ResponseTemplateVO;
import com.minhnk.post.VO.SendDataVO;
import com.minhnk.post.constant.ApiUrl;
import com.minhnk.post.entity.Post;
import com.minhnk.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;

    public Post createPost(Post post) {
        Post savedPost = postRepository.save(post);
        String sendingResult = this.sendDataToEvenBus(savedPost);
        return savedPost;
    }

    public String sendDataToEvenBus(Post savedPost){
        SendDataVO sendDataVO = new SendDataVO();
        sendDataVO.setType("Post created");
        sendDataVO.setId(savedPost.getId());
        sendDataVO.setTitle(savedPost.getTitle());

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<SendDataVO> entity = new HttpEntity<SendDataVO>(sendDataVO, headers);

        String result = restTemplate.exchange(ApiUrl.EVEN_BUS_SERVICE_API_URL, HttpMethod.POST, entity, String.class).getBody();
        return result;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId);
    }

    public List<ResponseTemplateVO> getAllPostWithComments() {
        List<ResponseTemplateVO> voList = new ArrayList<ResponseTemplateVO>();
        List<Post> postList = this.getAllPosts();
        for(Post post : postList){
            ResponseTemplateVO vo = new ResponseTemplateVO();
            Long postId = post.getId();
            ResponseEntity<Comment[]> responseEntity = restTemplate.getForEntity(ApiUrl.COMMENT_SERVICE_API_URL + post.getId(), Comment[].class);
            Comment[] commentArr = responseEntity.getBody();
            List<Comment> commentList = Arrays.asList(commentArr);
            vo.setPost(post);
            vo.setCommentList(commentList);
            voList.add(vo);
        }
        return voList;
    }

    public String getMessageFromEvenBus(String message) {
        System.out.println(message);
        System.out.println("POST SERVICE is running on port: " + port);
        return message;
    }
}
