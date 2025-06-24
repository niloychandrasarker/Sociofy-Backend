package com.niloy.service;

import com.niloy.models.Comment;
import org.hibernate.annotations.Comments;

public interface CommentService {
    public Comment createComment(Comment comment, Integer userId, Integer postId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likeComment(Integer commentId, Integer userId) throws Exception;
}
