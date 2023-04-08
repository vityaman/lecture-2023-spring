package ru.vityaman.demo.mailbox.database.jpa;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface JpaMailboxRepository
        extends CrudRepository<MailboxEntity, Integer> {
    Optional<MailboxEntity> findByAddress(String address);
}
