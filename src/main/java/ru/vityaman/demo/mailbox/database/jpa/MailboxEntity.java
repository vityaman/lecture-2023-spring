package ru.vityaman.demo.mailbox.database.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vityaman.demo.mailbox.model.Mailbox;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailboxEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Column(name = "address", unique = true, nullable = false)
    String address;

    public Mailbox toModel() {
        return new Mailbox(
            new Mailbox.Id(id), 
            new Mailbox.Address(address)
        );
    }
}
