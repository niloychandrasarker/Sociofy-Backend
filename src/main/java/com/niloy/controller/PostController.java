package com.niloy.controller;

import com.niloy.models.Post;
import com.niloy.response.ApiResponse;
import com.niloy.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer userId) throws Exception {
        Post createPost = postService.createNewPost(post, userId);
        return new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) {
        String message = postService.deletePost(postId, userId);
        ApiResponse response = new ApiResponse(message, true);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostsByIdHandler(@PathVariable Integer postId) {

        Post post = postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPosts(@PathVariable Integer userId) {
        List<Post> posts = postService.findPostsByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> posts = postService.findAllPosts();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }
    @PutMapping("/posts/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.savePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.OK); // Changed to HttpStatus.OK for better clarity
    }
    @PutMapping("/posts/like/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        Post post = postService.likePost(postId, userId);
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
