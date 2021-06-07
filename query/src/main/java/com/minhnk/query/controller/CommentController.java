package com.minhnk.query.controller;

import com.minhnk.query.entity.Comment;
import com.minhnk.query.entity.Post;
import com.minhnk.query.exception.PostNotFoundException;
import com.minhnk.query.repository.CommentRepository;
import com.minhnk.query.repository.PostRepository;
import com.minhnk.query.service.CommentService;
import com.minhnk.query.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/query")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Object> createComment(@PathVariable Long id, @RequestBody Comment comment) {
        //Optional<Post> postOptional = postRepository.findById(id);
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            throw new PostNotFoundException("id-" + id);
        }

        Post post = postOptional.get();
        comment.setPost(post);

        commentService.save(comment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
