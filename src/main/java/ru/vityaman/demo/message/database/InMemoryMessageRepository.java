package ru.vityaman.demo.message.database;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

public class InMemoryMessageRepository implements MessageRepository {
    private final Clock clock;

    private final List<Message> messages;
    private final NavigableSet<Integer> indexBySenderId;
    private final NavigableSet<Integer> indexByReceiverId;

    public InMemoryMessageRepository(Clock clock) {
        this.clock = clock;

        messages = new ArrayList<>();

        indexBySenderId = new TreeSet<>(Comparator
                .comparing((index) -> messages.get(index)
                        .getSenderId().getValue()));

        indexByReceiverId = new TreeSet<>(Comparator
                .comparing((index) -> messages.get(index)
                        .getReceiverId().getValue()));
    }

    @Override
    public synchronized Message createMessage(MessageDraft message) {
        final var insertionIndex = messages.size();

        final var item = new Message(
                new Message.Id(insertionIndex),
                clock.instant(),
                message.getSenderId(),
                message.getReceiverId(),
                message.getBody());

        messages.add(item);
        indexBySenderId.add(insertionIndex);
        indexByReceiverId.add(insertionIndex);

        return item;
    }

    @Override
    public Collection<Message> getAllMessagesWithSenderId(Mailbox.Id senderId) {
        final var indexes = indexBySenderId.subSet(
                senderId.getValue(), true, senderId.getValue(), true);
        return messages.subList(indexes.first(), indexes.last());
    }

    @Override
    public Collection<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId) {
        final var indexes = indexByReceiverId.subSet(
                receiverId.getValue(), true, receiverId.getValue(), true);
        return messages.subList(indexes.first(), indexes.last());
    }
}
