package ru.vityaman.demo.mailbox;

import lombok.experimental.StandardException;

@StandardException
public class MailboxAddressAlreadyInUseException extends Exception {
    public MailboxAddressAlreadyInUseException(Mailbox.Address address) {
        this(String.format(
                "Mailbox address %s already in use", address.getValue()));
    }
}
