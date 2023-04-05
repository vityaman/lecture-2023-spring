package ru.vityaman.demo.message.api.view;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

@Jacksonized
@Builder
@Value
public class MessageDraftView {
    int senderId;
    int receiverId;
    String body;

    public MessageDraft toModel() {
        return new MessageDraft(
            new Mailbox.Id(senderId),
            new Mailbox.Id(receiverId),
            new Message.Body(body)
        );
    }
}
