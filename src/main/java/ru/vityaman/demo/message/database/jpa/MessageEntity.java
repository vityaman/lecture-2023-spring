package ru.vityaman.demo.message.database.jpa;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vityaman.demo.mailbox.database.jpa.MailboxEntity;
import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false, updatable = false)
    Date timestamp;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    MailboxEntity sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    MailboxEntity receiver;

    @Column(name = "text", nullable = false)
    String text;

    public Message toModel() {
        return new Message(
                new Message.Id(id),
                timestamp.toInstant(),
                new Mailbox.Id(sender.getId()),
                new Mailbox.Id(receiver.getId()),
                new Message.Body(text));
    }
}
