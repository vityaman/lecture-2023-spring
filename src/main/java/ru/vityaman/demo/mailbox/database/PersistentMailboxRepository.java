package ru.vityaman.demo.mailbox.database;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import ru.vityaman.demo.mailbox.database.jpa.JpaMailboxRepository;
import ru.vityaman.demo.mailbox.database.jpa.entity.MailboxEntity;
import ru.vityaman.demo.mailbox.error.MailboxAddressAlreadyInUseException;
import ru.vityaman.demo.mailbox.error.MailboxNotFoundException;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.mailbox.model.Mailbox.Id;
import ru.vityaman.demo.mailbox.model.MailboxDraft;

@Repository
public class PersistentMailboxRepository implements MailboxRepository {
    private final JpaMailboxRepository repository;

    public PersistentMailboxRepository(JpaMailboxRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mailbox createMailbox(MailboxDraft mailbox) throws MailboxAddressAlreadyInUseException {
        try {
            final var inserted = MailboxEntity.builder()
                    .address(mailbox.getPublicAddress().getValue())
                    .build();
            final var saved = repository.save(inserted);
            return saved.toModel();
        } catch (DataIntegrityViolationException e) {
            throw new MailboxAddressAlreadyInUseException(
                mailbox.getPublicAddress(), e
            );
        }
    }

    @Override
    public Mailbox getMailboxById(Id id) throws MailboxNotFoundException {
        return repository
                .findById(id.getValue())
                .orElseThrow(() -> new MailboxNotFoundException(id))
                .toModel();
    }

}
