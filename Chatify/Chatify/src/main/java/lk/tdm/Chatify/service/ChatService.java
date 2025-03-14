package lk.tdm.Chatify.service;

import lk.tdm.Chatify.dto.MessageDTO;

import java.util.List;

public interface ChatService {
    void sendMessage(MessageDTO messageDTO);
    List<MessageDTO> getChatHistory(Long senderId, Long receiverId);
}
