package lk.tdm.Chatify.service.impl;

import lk.tdm.Chatify.dto.MessageDTO;
import lk.tdm.Chatify.entity.Message;
import lk.tdm.Chatify.entity.User;
import lk.tdm.Chatify.repo.MessageRepository;
import lk.tdm.Chatify.repo.UserRepo;
import lk.tdm.Chatify.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(MessageDTO messageDTO) {
        User sender = userRepo.findById(messageDTO.getSenderId()).orElseThrow();
        User receiver = userRepo.findById(messageDTO.getReceiverId()).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDTO.getContent());
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);

        messagingTemplate.convertAndSendToUser(
                receiver.getUsername(), "/topic/messages",
                new MessageDTO(savedMessage.getId(), savedMessage.getSender().getId(), savedMessage.getReceiver().getId(), savedMessage.getContent(), savedMessage.getTimestamp())
        );
    }

    @Override
    public List<MessageDTO> getChatHistory(Long senderId, Long receiverId) {
        List<Message> messages = messageRepository.findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(senderId, receiverId, receiverId, senderId);
        return messages.stream().map(message -> new MessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getReceiver().getId(),
                message.getContent(),
                message.getTimestamp()
        )).collect(Collectors.toList());
    }
}
