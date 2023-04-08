package ru.vityaman.demo.message.database;

import java.time.Clock;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import ru.vityaman.demo.mailbox.database.jpa.MailboxEntity;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox.Id;
import ru.vityaman.demo.message.database.jpa.JpaMessageRepository;
import ru.vityaman.demo.message.database.jpa.MessageEntity;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

@Repository
public class PersistentMessageRepository implements MessageRepository {
    private final Clock clock;
    private final JpaMessageRepository repository;

    public PersistentMessageRepository(Clock clock, JpaMessageRepository repository) {
        this.clock = clock;
        this.repository = repository;
    }

    @Override
    public Message createMessage(MessageDraft message) throws MailboxNotFoundException {
        try {
            return repository.save(MessageEntity.builder()
                    .timestamp(Date.from(clock.instant()))
                    .sender(MailboxEntity.builder()
                            .id(message.getSenderId().getValue())
                            .build())
                    .receiver(MailboxEntity.builder()
                            .id(message.getReceiverId().getValue())
                            .build())
                    .text(message.getBody().getText())
                    .build())
                    .toModel();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new MailboxNotFoundException(message.getSenderId(), message.getReceiverId());
        }
    }

    @Override
    public Stream<Message> getAllMessagesWithSenderId(Id senderId) {
        return repository
                .findBySenderId(senderId.getValue())
                .map(MessageEntity::toModel);
    }

    @Override
    public Stream<Message> getAllMessagesWithReceiverId(Id receiverId) {
        return repository
                .findByReceiverId(receiverId.getValue())
                .map(MessageEntity::toModel);
    }

}
