package ru.vityaman.demo.mailbox.database;

import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

public interface MailboxRepository {
    Mailbox createMailbox(MailboxDraft mailbox)
            throws MailboxAddressAlreadyInUseException;

    Mailbox getMailboxById(Mailbox.Id id)
            throws MailboxNotFoundException;

    Mailbox getMailboxByAddress(Mailbox.Address address)
            throws MailboxNotFoundException;
}
