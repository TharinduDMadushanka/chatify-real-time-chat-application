package lk.tdm.Chatify.service.impl;

import lk.tdm.Chatify.dto.MessageDTO;
import lk.tdm.Chatify.entity.Message;
import lk.tdm.Chatify.entity.User;
import lk.tdm.Chatify.repo.MessageRepository;
import lk.tdm.Chatify.repo.UserRepo;
import lk.tdm.Chatify.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public void saveMessage(MessageDTO messageDTO) {
        User sender = userRepo.findById(messageDTO.getSenderId()).orElseThrow();
        User receiver = userRepo.findById(messageDTO.getReceiverId()).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(messageDTO.getContent());
        message.setTimestamp(LocalDateTime.now());

        messageRepository.save(message);
    }

    @Override
    public Page<MessageDTO> getChatHistory(Long senderId, Long receiverId, Pageable pageable) {
        Page<Message> messages = messageRepository.findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(senderId, receiverId, receiverId, senderId, pageable);
        return messages.map(message -> new MessageDTO(
                message.getId(),
                message.getSender().getId(),
                message.getReceiver().getId(),
                message.getContent(),
                message.getTimestamp()
        ));
    }
}
