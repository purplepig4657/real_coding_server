package com.cnu.real_coding_server.controller;

import com.cnu.real_coding_server.entity.Post;
import com.cnu.real_coding_server.model.request.PostRequest;
import com.cnu.real_coding_server.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(this.postService.getPost(postId).orElse(null));
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok(this.postService.getPosts());
    }

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(this.postService.createPost(postRequest.toEntity()));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(
            @PathVariable("postId") Integer postId,
            @RequestBody PostRequest postRequest
    ) {
        return ResponseEntity.ok(this.postService.updatePost(postId, postRequest.toEntity()));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") Integer postId) {
        this.postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
