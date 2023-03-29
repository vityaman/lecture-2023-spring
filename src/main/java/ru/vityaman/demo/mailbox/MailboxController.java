package ru.vityaman.demo.mailbox;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/mailbox")
class MailboxController {
    private final MailboxService service;

    public MailboxController(MailboxService service) {
        this.service = service;
    }

    @PostMapping
    int createMailbox(@RequestBody MailboxDraftView mailbox) {
        try {
            return service
                    .createMailbox(mailbox.toModel())
                    .getId()
                    .getValue();
        } catch (MailboxAddressAlreadyInUseException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                e.getMessage(),
                e
            );
        }
    }
}
