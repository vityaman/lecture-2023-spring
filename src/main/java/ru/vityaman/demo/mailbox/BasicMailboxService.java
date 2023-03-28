package ru.vityaman.demo.mailbox;

import org.springframework.stereotype.Service;

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
}
