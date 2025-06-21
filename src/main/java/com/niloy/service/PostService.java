package com.niloy.service;

import com.niloy.models.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer userId) throws Exception;
    String deletePost(Integer postId, Integer userId);
    List<Post> findPostsByUserId(Integer userId);
    Post findPostById(Integer postId);
    List<Post> findAllPosts();
    Post savePost(Integer postId, Integer userId) throws Exception;
    Post likePost(Integer postId, Integer userId) throws Exception;

}
