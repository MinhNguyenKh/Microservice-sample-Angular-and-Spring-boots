package com.minhnk.post.controller;

import com.minhnk.post.VO.Comment;
import com.minhnk.post.VO.ResponseTemplateVO;
import com.minhnk.post.entity.Post;
import com.minhnk.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public Post createPost(@RequestBody Post post){
        log.info("PostController - creating post");
        return postService.createPost(post);
    }

    @GetMapping("/")
    public List<Post> getAllPosts(){
        log.info("PostController - getting all posts");
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post findPostById(@PathVariable("id") Long postId){
        log.info("PostController - finding post");
        return postService.findPostById(postId);
    }

    @GetMapping("/post-comment")
    public List<ResponseTemplateVO> getAllPostWithComments(){
        return postService.getAllPostWithComments();
    }

    @PostMapping("/events")
    public String getMessageFromEvenBus(@RequestBody String message){
        return postService.getMessageFromEvenBus(message);
    }
}
