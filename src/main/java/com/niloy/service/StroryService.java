package com.niloy.service;

import com.niloy.models.Story;
import com.niloy.models.User;

import java.util.List;

public interface StroryService {
    public Story createStory(Story story, User user) throws Exception;
    public List<Story> findStoryByUserId(Integer userId) throws Exception;
}
