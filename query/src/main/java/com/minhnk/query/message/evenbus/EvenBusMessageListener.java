package com.minhnk.query.message.evenbus;

import com.minhnk.query.entity.Comment;
import com.minhnk.query.entity.Post;
import com.minhnk.query.service.CommentService;
import com.minhnk.query.service.PostService;
import com.minhnk.query.service.QueryService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EvenBusMessageListener {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @RabbitListener(queues = EvenBusMQConfig.QUEUE)
    public void listener(CustomMessageData customMessageData){
        System.out.println(customMessageData);
        if("Post".equalsIgnoreCase(customMessageData.getType())){
            Post post = new Post();
            post.setId(customMessageData.getPostId());
            post.setTitle(customMessageData.getTitle());
            postService.save(post);
        }else{
            Post post = postService.findPostById(customMessageData.getPostId());
            Comment comment = new Comment();
            comment.setId(customMessageData.getCommentId());
            comment.setContent(customMessageData.getContent());
            comment.setPost(post);
            commentService.save(comment);
        }
    }
}
