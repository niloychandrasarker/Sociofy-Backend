package com.niloy.controller;

import com.niloy.models.Message;
import com.niloy.models.User;
import com.niloy.service.MessageService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

@PostMapping("/api/Messages/chat/{chatId}")
        public Message createMessage(@RequestBody Message req, @RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception {
            User user = userService.findUserByJwt(token);
            Message message = messageService.createMessage(user, chatId, req);
            return message;
        }
        @GetMapping("/api/Messages/chat/{chatId}")
        public List<Message> findChatMessages(@RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception {
            User user = userService.findUserByJwt(token);
            List<Message> messages = messageService.findChatsMessages(chatId);
            return messages;
        }
}
