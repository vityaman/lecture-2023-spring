package ru.vityaman.demo.message.api;

import java.time.ZoneOffset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import ru.vityaman.demo.api.model.MessageView;
import ru.vityaman.demo.api.MessagesApiDelegate;
import ru.vityaman.demo.api.model.ConversationView;
import ru.vityaman.demo.api.model.MessageDraftView;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;
import ru.vityaman.demo.message.service.MessageService;

@Service
public class MessageApi implements MessagesApiDelegate {
    private final MessageService service;

    public MessageApi(MessageService service) {
        this.service = service;
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<ConversationView> messagesGet(Integer a, Integer b) {
        final var aid = new Mailbox.Id(a);
        final var bid = new Mailbox.Id(b);
        final var messages = service
                .getAllMessagesBetween(aid, bid).stream()
                .map(this::convertToMessageView)
                .toList();
        final var conversation = new ConversationView()
                .messages(messages);
        return ResponseEntity.ok(conversation);
    }

    @Override
    public ResponseEntity<MessageView> messagesPost(MessageDraftView messageDraftView) {
        try {
            final var messageDraft = new MessageDraft(
                new Mailbox.Id(messageDraftView.getSenderId()),
                new Mailbox.Id(messageDraftView.getReceiverId()),
                new Message.Body(messageDraftView.getText())
            );
            final var message = service.sendMessage(messageDraft);
            return ResponseEntity.ok(convertToMessageView(message));
        } catch (MailboxNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    private MessageView convertToMessageView(Message message) {
        return new MessageView()
        .id(message.getId().getValue())
        .timestamp(message.getTimestamp().atOffset(ZoneOffset.UTC))
        .senderId(message.getSenderId().getValue())
        .receiverId(message.getReceiverId().getValue())
        .text(message.getBody().getText());
    }

}
