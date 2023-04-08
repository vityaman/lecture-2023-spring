package ru.vityaman.demo.message.database.jpa;

import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

public interface JpaMessageRepository 
    extends CrudRepository<MessageEntity, Integer> {
    
    Stream<MessageEntity> findBySenderId(Integer senderId);
    Stream<MessageEntity> findByReceiverId(Integer receiverId);
}
