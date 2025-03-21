package lk.tdm.Chatify.service;

import lk.tdm.Chatify.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatService {
    void sendMessage(MessageDTO messageDTO);
    Page<MessageDTO> getChatHistory(Long senderId, Long receiverId, Pageable pageable);
}
