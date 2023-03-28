package ru.vityaman.demo.mailbox;

interface MailboxService {
    Mailbox createMailbox(MailboxDraft mailbox) throws MailboxAddressAlreadyInUseException;
}
