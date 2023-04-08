package ru.vityaman.demo.mailbox.database;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.Mailbox.Address;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

final class InMemoryMailboxRepository implements MailboxRepository {
    private final ArrayList<Mailbox> mailboxes;
    private final Map<String, Integer> indexByAddress;

    public InMemoryMailboxRepository() {
        this.mailboxes = new ArrayList<>();
        this.indexByAddress = new TreeMap<>();
    }

    @Override
    public synchronized Mailbox createMailbox(MailboxDraft mailbox) throws MailboxAddressAlreadyInUseException {
        if (indexByAddress.containsKey(mailbox.getPublicAddress().getValue())) {
            throw new MailboxAddressAlreadyInUseException(
                    mailbox.getPublicAddress());
        }
        var inserted = new Mailbox(
                new Mailbox.Id(nextId()),
                mailbox.getPublicAddress());
        mailboxes.add(inserted);
        indexByAddress.put(
                inserted.getPublicAddress().getValue(),
                inserted.getId().getValue());

        System.out.println("Added new mailbox");
        System.out.println(mailboxes);

        return inserted;
    }

    @Override
    public synchronized Mailbox getMailboxById(Mailbox.Id id) throws MailboxNotFoundException {
        if (mailboxes.size() <= id.getValue()) {
            throw new MailboxNotFoundException(id);
        }
        return mailboxes.get(id.getValue());
    }

    @Override
    public synchronized Mailbox getMailboxByAddress(Address address) throws MailboxNotFoundException {
        final var index = indexByAddress.get(address.getValue());
        if (index == null) {
            throw new MailboxNotFoundException(address);
        }
        return mailboxes.get(index);
    }

    private int nextId() {
        return mailboxes.size();
    }

}
