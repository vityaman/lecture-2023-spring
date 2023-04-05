package ru.vityaman.demo.message.database;

import java.util.stream.Stream;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public interface MessageRepository {
    Message createMessage(MessageDraft message);
    Stream<Message> getAllMessagesWithSenderId(Mailbox.Id senderId);
    Stream<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId);
}
