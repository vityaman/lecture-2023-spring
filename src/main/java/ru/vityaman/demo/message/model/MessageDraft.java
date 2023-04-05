package ru.vityaman.demo.message.model;

import lombok.Value;
import ru.vityaman.demo.mailbox.model.Mailbox;

@Value
public class MessageDraft {
    Mailbox.Id senderId;
    Mailbox.Id receiverId;
    Message.Body body;
}
