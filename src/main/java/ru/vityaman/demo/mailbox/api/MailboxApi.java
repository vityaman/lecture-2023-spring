package ru.vityaman.demo.mailbox.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ru.vityaman.demo.api.model.MailboxView;
import ru.vityaman.demo.api.model.MailboxDraftView;
import ru.vityaman.demo.api.MailboxesApiDelegate;
import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
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
            return ResponseEntity.ok(convert(mailbox));

        } catch (MailboxAddressAlreadyInUseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @Override
    public ResponseEntity<MailboxView> mailboxesGet(String address) {
        try {
            final var mailbox = service.getMailboxWithAddress(new Mailbox.Address(address));
            return ResponseEntity.ok(convert(mailbox));
        } catch (MailboxNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    private MailboxView convert(Mailbox model) {
        return new MailboxView()
                .id(model.getId().getValue())
                .publicAddress(model.getPublicAddress().getValue());
    }

}
