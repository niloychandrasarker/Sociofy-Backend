package com.niloy.controller;

import com.niloy.models.Reels;
import com.niloy.models.User;
import com.niloy.service.ReelsService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    private ReelsService reelsService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReel(@RequestBody Reels reel, @RequestHeader("Authorization") String token) throws Exception {

        User reqUser = userService.findUserByJwt(token);
        return reelsService.createReel(reel,reqUser);
    }

    @GetMapping("/api/reels")
    public List<Reels> findAllReels() {
        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("/api/reels/user/{userId}")
    public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
        List<Reels> reels = reelsService.findUsersReel(userId);
        return reels;
    }
}
