package ru.vityaman.demo.mailbox;

import lombok.Value;

@Value
class MailboxDraft {
    Mailbox.Address publicAddress;
}
