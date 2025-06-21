package com.niloy.service;

import com.niloy.models.Post;
import com.niloy.models.User;
import com.niloy.repository.PostRepository;
import com.niloy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplimentation implements PostService{
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {
        User user = userService.findUserById(userId);

        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
       Post post = findPostById(postId);
       User user = userService.findUserById(userId);

         if (!post.getUser().getId().equals(user.getId())) {
              throw new RuntimeException("You are not authorized to delete this post");
         }
         postRepository.delete(post);
            return "Post with id " + postId + " deleted successfully";

    }

    @Override
    public List<Post> findPostsByUserId(Integer userId) {
       return postRepository.findPostByUserId(userId);

    }

    @Override
    public Post findPostById(Integer postId) {
        Optional <Post> opt = postRepository.findById(postId);
        if (opt.isEmpty()) {
            throw new RuntimeException("Post not found with id: " + postId);
        }
        return opt.get();
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post savePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPosts().contains(post)) {
            user.getSavedPosts().remove(post);
        } else {
            user.getSavedPosts().add(post);
        }

        userRepository.save(user);
        return post;

    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findUserById(userId);

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }

        return postRepository.save(post);

    }
}
