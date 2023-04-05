package ru.vityaman.demo.mailbox.api;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

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
