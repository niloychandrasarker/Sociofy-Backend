package com.niloy.service;

import com.niloy.models.Chat;
import com.niloy.models.Message;
import com.niloy.models.User;

import java.util.List;

public interface MessageService {
    public Message createMessage(User user, Integer chatId, Message req) throws Exception;
    public List<Message> findChatsMessages(Integer chatId) throws Exception;
}
