package ru.vityaman.demo.mailbox;

interface MailboxRepository {
    Mailbox createMailbox(MailboxDraft mailbox)
            throws MailboxAddressAlreadyInUseException;

    Mailbox getMailboxById(Mailbox.Id id)
            throws MailboxNotFoundException;
}
