package lk.tdm.Chatify.controller;

import lk.tdm.Chatify.dto.MessageDTO;
import lk.tdm.Chatify.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class ChatController {

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        messageService.saveMessage(messageDTO);
    }

    @GetMapping("/chat/history")
    public Page<MessageDTO> getChatHistory(@RequestParam Long senderId, @RequestParam Long receiverId, Pageable pageable) {
        return messageService.getChatHistory(senderId, receiverId, pageable);
    }
}
