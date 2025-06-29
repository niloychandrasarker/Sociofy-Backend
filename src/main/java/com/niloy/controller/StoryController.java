package com.niloy.controller;

import com.niloy.models.Story;
import com.niloy.models.User;
import com.niloy.service.StroryService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StroryService stroryService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String token) throws Exception {
        User reqUser = userService.findUserByJwt(token);
        Story CreateStory = stroryService.createStory(story, reqUser);
        return CreateStory;
    }
@PostMapping("/api/story/user/{userId}")
        public List<Story> findUsersStory(@PathVariable Integer userId, @RequestHeader("Authorization") String token) throws Exception {
            User reqUser = userService.findUserByJwt(token);
            List<Story> stories = stroryService.findStoryByUserId(userId);
            return stories;
        }
}

