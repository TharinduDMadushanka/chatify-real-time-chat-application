package lk.tdm.Chatify.controller;

import lk.tdm.Chatify.dto.MessageDTO;
import lk.tdm.Chatify.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("api/v1/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload MessageDTO messageDTO) {
        chatService.sendMessage(messageDTO);
    }

    @GetMapping("/history")
    public List<MessageDTO> getChatHistory(@RequestParam Long senderId, @RequestParam Long receiverId) {
        return chatService.getChatHistory(senderId, receiverId);
    }
}
