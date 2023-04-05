package ru.vityaman.demo.message.api.view;

import java.time.Instant;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.vityaman.demo.message.model.Message;

@Jacksonized
@Builder
@Value
public class MessageView {
    int id;
    Instant timestamp;
    int senderId;
    int receiverId;
    String text;

    public static MessageView fromModel(Message message) {
        return new MessageView(
            message.getId().getValue(),
            message.getTimestamp(),
            message.getSenderId().getValue(),
            message.getReceiverId().getValue(),
            message.getBody().getText()
        );
    }
}
