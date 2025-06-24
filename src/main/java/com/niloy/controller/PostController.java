package com.niloy.controller;

import com.niloy.models.Post;
import com.niloy.models.User;
import com.niloy.response.ApiResponse;
import com.niloy.service.PostService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;


    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token, @RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(token);
        Post createPost = postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(createPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String token) {
        User reqUser = userService.findUserByJwt(token);
        String message = postService.deletePost(postId, reqUser.getId());
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
    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws Exception {
        User reqUser = userService.findUserByJwt(token);
        Post post = postService.savePost(postId, reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.OK); // Changed to HttpStatus.OK for better clarity
    }
    @PutMapping("/posts/like/{postId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String token) throws Exception {
        User reqUser = userService.findUserByJwt(token);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }
}
