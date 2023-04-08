package ru.vityaman.demo.mailbox.error;

import ru.vityaman.demo.mailbox.model.Mailbox;

public class MailboxAddressAlreadyInUseException extends Exception {
    public MailboxAddressAlreadyInUseException(Mailbox.Address address) {
        this(address, null);
    }

    public MailboxAddressAlreadyInUseException(Mailbox.Address address, Throwable cause) {
        super(String.format(
                "Mailbox address %s already in use", address.getValue()), cause);
    }
}
