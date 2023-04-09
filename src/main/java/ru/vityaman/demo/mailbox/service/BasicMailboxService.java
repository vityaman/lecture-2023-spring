package ru.vityaman.demo.mailbox.service;

import org.springframework.stereotype.Service;

import ru.vityaman.demo.mailbox.database.MailboxRepository;
import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.Mailbox.Address;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

@Service
class BasicMailboxService implements MailboxService {
    private final MailboxRepository repository;

    public BasicMailboxService(MailboxRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mailbox createMailbox(MailboxDraft mailbox) throws MailboxAddressAlreadyInUseException {
        return repository.createMailbox(mailbox);
    }

    @Override
    public Mailbox getMailboxWithAddress(Address address) throws MailboxNotFoundException {
        return repository.getMailboxByAddress(address);
    }   
}
