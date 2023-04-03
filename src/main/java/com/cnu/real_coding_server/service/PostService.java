package com.cnu.real_coding_server.service;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPost(Integer postId) {
        return this.postRepository.findById(postId);
    }

    public List<Post> getPosts() {
        return this.postRepository.findAll();
    }

    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }

    public Post updatePost(Integer postId, Post updatedPost) {
        Post targetPost = this.postRepository.findById(postId).orElse(null);
        Assert.notNull(targetPost, "target post must not be null.");
        targetPost.setTitle(updatedPost.getTitle());
        targetPost.setContents(updatedPost.getContents());
        targetPost.setTag(updatedPost.getTag());
        return targetPost;
    }

    public void deletePost(Integer targetPostId) {
        Optional<Post> targetPost = this.postRepository.findById(targetPostId);
        targetPost.ifPresent(this.postRepository::delete);
    }
}
