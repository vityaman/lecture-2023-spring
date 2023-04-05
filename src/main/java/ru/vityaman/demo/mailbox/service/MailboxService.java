package ru.vityaman.demo.mailbox.service;

import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

public interface MailboxService {
    Mailbox createMailbox(MailboxDraft mailbox) throws MailboxAddressAlreadyInUseException;
}
