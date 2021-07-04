package com.minhnk.query.controller;

import com.minhnk.query.entity.Post;
import com.minhnk.query.repository.PostRepository;
import com.minhnk.query.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/query")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postService.findAll();
    }

    @GetMapping("/posts/{status}")
    public List<Post> findAllPostByStatus(@PathVariable String status) {
        return postService.findAllPostByStatus(status);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post savedPost = postService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/updatePosts")
    public List<Post> updatePost(@RequestBody List<Post> postList) {
        if(postList != null && !postList.isEmpty()){
            return postService.updatePostStatus(postList);
        }
        return null;
    }
}
