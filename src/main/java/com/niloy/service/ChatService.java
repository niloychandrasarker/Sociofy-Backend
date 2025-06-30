package com.niloy.service;

import com.niloy.models.Chat;
import com.niloy.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user2) throws Exception;
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId) throws Exception;
}
