package ru.vityaman.demo.mailbox;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
class MailboxDraftView {
    String publicAddress;

    MailboxDraft toModel() {
        return new MailboxDraft(
            new Mailbox.Address(publicAddress)
        );
    }
}
