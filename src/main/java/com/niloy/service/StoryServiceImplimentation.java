package com.niloy.service;

import com.niloy.models.Story;
import com.niloy.models.User;
import com.niloy.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StoryServiceImplimentation implements StroryService {

    @Autowired
    private StoryRepository stroryRepository;
    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, User user) throws Exception {

        Story createStory = new Story();
        createStory.setCaption(story.getCaption());
        createStory.setImage(story.getImage());
        createStory.setUser(user);
        createStory.setTimestamp(story.getTimestamp());
        return stroryRepository.save(createStory);
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return stroryRepository.findByUserId(userId);
    }
}
