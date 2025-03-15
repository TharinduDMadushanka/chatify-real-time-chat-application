package lk.tdm.Chatify.repo;

import lk.tdm.Chatify.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Page<Message> findAllBySenderIdAndReceiverIdOrSenderIdAndReceiverId(Long senderId, Long receiverId, Long receiverId2, Long senderId2, Pageable pageable);
}
