package ru.vityaman.demo.message.service;

import java.util.Collection;
import java.util.stream.Stream;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.database.MessageRepository;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public class BasicMessageService implements MessageService {
    private final MessageRepository repository;

    public BasicMessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message sendMessage(MessageDraft message) {
        return repository.createMessage(message);
    }

    @Override
    public Collection<Message> getAllMessagesBetween(Mailbox.Id a, Mailbox.Id b) {
        // TODO: Can be optimized in repository
        return Stream.concat(
                repository.getAllMessagesWithSenderId(a)
                        .filter((message) -> message.getReceiverId().equals(b)),
                repository.getAllMessagesWithSenderId(b)
                        .filter((message) -> message.getReceiverId().equals(a)))
                .toList();
    }

    @Override
    public Collection<Message> getAllMessagesWithSenderId(Mailbox.Id senderId) {
        return repository.getAllMessagesWithSenderId(senderId).toList();
    }

    @Override
    public Collection<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId) {
        return repository.getAllMessagesWithReceiverId(receiverId).toList();
    }

}
