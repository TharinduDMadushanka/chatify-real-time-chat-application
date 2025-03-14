package lk.tdm.Chatify.repo;

import lk.tdm.Chatify.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(Long senderId, Long receiverId, Long receiverId1, Long senderId1);
}
