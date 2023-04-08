package ru.vityaman.demo.message.database;

import java.util.stream.Stream;

import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public interface MessageRepository {
    Message createMessage(MessageDraft message) throws MailboxNotFoundException;
    Stream<Message> getAllMessagesWithSenderId(Mailbox.Id senderId);
    Stream<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId);
    Stream<Message> getConversationBetween(Mailbox.Id a, Mailbox.Id b);
}
