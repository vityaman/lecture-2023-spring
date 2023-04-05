package ru.vityaman.demo.mailbox.error;

import lombok.experimental.StandardException;
import ru.vityaman.demo.mailbox.model.Mailbox;

@StandardException
public class MailboxAddressAlreadyInUseException extends Exception {
    public MailboxAddressAlreadyInUseException(Mailbox.Address address) {
        this(String.format(
                "Mailbox address %s already in use", address.getValue()));
    }
}
