package com.minhnk.query.service;

import com.minhnk.query.entity.Post;
import com.minhnk.query.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findPostById(Long id) {
        return postRepository.findPostById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
