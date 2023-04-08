package ru.vityaman.demo.mailbox.error;

import lombok.experimental.StandardException;
import ru.vityaman.demo.mailbox.model.Mailbox;

@StandardException
public class MailboxNotFoundException extends Exception {
    public MailboxNotFoundException(Mailbox.Id id) {
        this(String.format("Mailbox with id %s not found", id.getValue()));
    }

    public MailboxNotFoundException(Mailbox.Address address) {
        this(String.format("Mailbox with address %s not found", address.getValue()));
    }
}
