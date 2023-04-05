package ru.vityaman.demo.message.service;

import java.util.Collection;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public interface MessageService {
    Message sendMessage(MessageDraft message);

    Collection<Message> getAllMessagesBetween(Mailbox.Id a, Mailbox.Id b);

    Collection<Message> getAllMessagesWithSenderId(Mailbox.Id senderId);

    Collection<Message> getAllMessagesWithReceiverId(Mailbox.Id senderId);
}
