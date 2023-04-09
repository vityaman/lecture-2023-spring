package ru.vityaman.demo.message.database.jpa;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface JpaMessageRepository
        extends CrudRepository<MessageEntity, Integer> {

    Stream<MessageEntity> findBySenderId(Integer senderId);

    Stream<MessageEntity> findByReceiverId(Integer receiverId);

    @Query("SELECT m FROM MessageEntity m " +
            "WHERE m.sender.id = :a AND m.receiver.id = :b " +
            "OR    m.sender.id = :b AND m.receiver.id = :a")
    Stream<MessageEntity> getConversationBetween(
            @Param("a") Integer a, @Param("b") Integer b);
}
