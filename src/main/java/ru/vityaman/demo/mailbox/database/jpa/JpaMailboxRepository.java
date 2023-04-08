package ru.vityaman.demo.mailbox.database.jpa;

import org.springframework.data.repository.CrudRepository;

import ru.vityaman.demo.mailbox.database.jpa.entity.MailboxEntity;

public interface JpaMailboxRepository
        extends CrudRepository<MailboxEntity, Integer> {

}
