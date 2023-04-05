package ru.vityaman.demo.message.model;

import java.time.Instant;

import lombok.Value;
import ru.vityaman.demo.mailbox.model.Mailbox;

@Value
public class Message {
    Id id;
    Instant timestamp;
    Mailbox.Id senderId;
    Mailbox.Id receiverId;
    Body body;

    @Value
    static public class Id {
        int value;
    }

    @Value
    static public class Body {
        String text;
    }
}
