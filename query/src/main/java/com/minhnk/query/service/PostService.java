package com.minhnk.query.service;

import com.minhnk.query.entity.Post;
import com.minhnk.query.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        post.setStatus("P");
        return postRepository.save(post);
    }

    public List<Post> updatePostStatus(List<Post> postList) {
        List<Post> updatedPostList = new ArrayList<Post>();
        for(Post post : postList){
            post.setStatus("A");
            updatedPostList.add(post);
        }
        return postRepository.saveAll(updatedPostList);
    }

    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllPostByStatus(String status) {
        return postRepository.findAllPostByStatus(status);
    }
}
