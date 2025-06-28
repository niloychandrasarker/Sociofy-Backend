package com.niloy.service;

import com.niloy.models.Reels;
import com.niloy.models.User;
import com.niloy.repository.ReelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplimentation implements ReelsService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReelRepository reelRepository;

    @Override
    public Reels createReel(Reels reels, User user) {
        Reels createReel = new Reels();
        createReel.setTitle(reels.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reels.getVideo());
        return reelRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
       return reelRepository.findAll();
    }

    @Override
    public List<Reels> findUsersReel(Integer userId) throws Exception {
        userService.findUserById(userId);
        return reelRepository.findByUserId(userId);
    }
}
