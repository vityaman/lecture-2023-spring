package ru.vityaman.demo.message.database;

import java.util.Collection;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public interface MessageRepository {
    Message createMessage(MessageDraft message);
    Collection<Message> getAllMessagesWithSenderId(Mailbox.Id senderId);
    Collection<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId);
}
