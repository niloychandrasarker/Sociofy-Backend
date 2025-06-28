package com.niloy.service;

import com.niloy.models.Reels;
import com.niloy.models.User;

import java.util.List;

public interface ReelsService{
    public Reels createReel(Reels reels, User user);
    public List<Reels>findAllReels();
    public List<Reels> findUsersReel(Integer userId) throws Exception;

}
