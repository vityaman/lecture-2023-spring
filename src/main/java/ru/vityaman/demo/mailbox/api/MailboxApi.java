package ru.vityaman.demo.mailbox.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.vityaman.demo.api.model.MailboxView;
import ru.vityaman.demo.api.model.MailboxDraftView;
import ru.vityaman.demo.api.MailboxesApiDelegate;
import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.model.MailboxDraft;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.service.MailboxService;

@Service
class MailboxApi implements MailboxesApiDelegate {
    private final MailboxService service;

    public MailboxApi(MailboxService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<MailboxView> mailboxesPost(MailboxDraftView mailboxDraftView) {
        try {
            final var draft = new MailboxDraft(
                    new Mailbox.Address(mailboxDraftView.getPublicAddress()));

            final var mailbox = service.createMailbox(draft);

            return ResponseEntity.ok(new MailboxView()
                    .id(mailbox.getId().getValue())
                    .publicAddress(mailbox.getPublicAddress().getValue()));
                    
        } catch (MailboxAddressAlreadyInUseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
