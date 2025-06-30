package com.niloy.controller;

import com.niloy.models.Chat;
import com.niloy.models.User;
import com.niloy.request.CreateChatRequest;
import com.niloy.service.ChatService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String token, @RequestBody CreateChatRequest req) throws Exception {
        // Assuming you have a method to get User by ID
        User reqUser = userService.findUserByJwt(token);
        User user2 = userService.findUserById(req.getUserId());
        Chat chat = chatService.createChat(reqUser, user2);

        return chat;
     }


    @GetMapping("/api/chats")
     public List<Chat> findUsersChat( @RequestHeader("Authorization") String token) throws Exception {
        // Assuming you have a method to get User by ID

        User user = userService.findUserByJwt(token);
        List<Chat> chats = chatService.findUsersChat(user.getId());

        return chats;
    }
}
