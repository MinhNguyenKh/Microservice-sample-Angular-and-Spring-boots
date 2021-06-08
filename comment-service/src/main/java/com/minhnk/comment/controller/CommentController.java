package com.minhnk.comment.controller;

import com.minhnk.comment.VO.ResponseTemplateVO;
import com.minhnk.comment.entity.Comment;
import com.minhnk.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/")
    public Comment createComment(@RequestBody Comment comment){
        return commentService.createComment(comment);
    }

    @GetMapping("/")
    public List<Comment> getAllComment(){
        return commentService.getAllComment();
    }

//    @GetMapping("/{id}")
//    public ResponseTemplateVO getCommentOfPost(@PathVariable("id") Long commentId){
//        return commentService.getCommentOfPost(commentId);
//    }

    @GetMapping("/{id}")
    public List<Comment> findCommentByPostId(@PathVariable("id") Long postId){
        return commentService.findCommentByPostId(postId);
    }

    @PostMapping("/events")
    public String getMessageFromEvenBus(@RequestBody String message){
        return commentService.getMessageFromEvenBus(message);
    }
}
