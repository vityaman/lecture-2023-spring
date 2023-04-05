package ru.vityaman.demo.message.api;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.api.view.MessageDraftView;
import ru.vityaman.demo.message.api.view.MessageView;
import ru.vityaman.demo.message.service.MessageService;

@RestController
@RequestMapping("/message")
class MessageController {
    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    int sendMessage(@RequestBody MessageDraftView message) {
        return service
                .sendMessage(message.toModel())
                .getId()
                .getValue();
    }

    @GetMapping
    Collection<MessageView> getConversation(
            @RequestParam int a,
            @RequestParam int b) {
        final var aid = new Mailbox.Id(a);
        final var bid = new Mailbox.Id(b);
        return service.getAllMessagesBetween(aid, bid).stream()
                .map(MessageView::fromModel)
                .toList();
    }
}
