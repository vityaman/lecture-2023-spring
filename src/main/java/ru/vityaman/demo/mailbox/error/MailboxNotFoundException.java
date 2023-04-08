package ru.vityaman.demo.mailbox.error;

import ru.vityaman.demo.mailbox.model.Mailbox;

public class MailboxNotFoundException extends Exception {
    public MailboxNotFoundException(Mailbox.Id id) {
        super(String.format("Mailbox with id %s not found", id.getValue()));
    }

    public MailboxNotFoundException(Mailbox.Address address) {
        super(String.format("Mailbox with address %s not found", address.getValue()));
    }

    public MailboxNotFoundException(Mailbox.Id a, Mailbox.Id b) {
        super(String.format(
            "Mailbox with id %s or %s not found", 
            a.getValue(), b.getValue()
        ));
    }
}
