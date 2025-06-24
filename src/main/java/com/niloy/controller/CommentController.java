package com.niloy.controller;

import com.niloy.models.Comment;
import com.niloy.models.User;
import com.niloy.service.CommentService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(
            @RequestBody Comment comment,
            @RequestHeader("Authorization") String token,
            @PathVariable("postId") Integer postId) throws Exception {
        User user = userService.findUserByJwt(token);
        return commentService.createComment(comment, user.getId(), postId);
    }
    @PutMapping ("/api/comments/like/{commentId}")
    public Comment likeComment(
            @RequestHeader("Authorization") String token,
            @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(token);
        return commentService.likeComment(commentId, user.getId());
    }

    @GetMapping("/api/comments/{commentId}")
    public Comment findCommentById(@PathVariable Integer commentId) throws Exception {
        return commentService.findCommentById(commentId);
    }
}
