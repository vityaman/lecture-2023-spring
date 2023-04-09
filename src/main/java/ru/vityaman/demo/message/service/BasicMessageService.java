package ru.vityaman.demo.message.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.database.MessageRepository;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

@Service
public class BasicMessageService implements MessageService {
    private final MessageRepository repository;

    public BasicMessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public Message sendMessage(MessageDraft message) throws MailboxNotFoundException {
        return repository.createMessage(message);
    }

    @Override
    public Collection<Message> getAllMessagesBetween(Mailbox.Id a, Mailbox.Id b) {
        return repository.getConversationBetween(a, b).toList();
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
