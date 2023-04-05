package ru.vityaman.demo.message.database;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import ru.vityaman.demo.mailbox.model.Mailbox;
import ru.vityaman.demo.message.model.Message;
import ru.vityaman.demo.message.model.MessageDraft;

@Repository
public class InMemoryMessageRepository implements MessageRepository {
    private final Clock clock;

    private final List<Message> messages;
    private final Map<Mailbox.Id, Set<Integer>> indexBySenderId;
    private final Map<Mailbox.Id, Set<Integer>> indexByReceiverId;

    public InMemoryMessageRepository(Clock clock) {
        this.clock = clock;

        messages = new ArrayList<>();
        indexBySenderId = new HashMap<>();
        indexByReceiverId = new HashMap<>();
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
        indexBySenderId
                .computeIfAbsent(item.getSenderId(), (k) -> new TreeSet<>())
                .add(insertionIndex);
        indexByReceiverId
                .computeIfAbsent(item.getReceiverId(), (k) -> new TreeSet<>())
                .add(insertionIndex);

        return item;
    }

    @Override
    public Stream<Message> getAllMessagesWithSenderId(Mailbox.Id senderId) {
        return indexBySenderId
                .getOrDefault(senderId, Collections.emptySet())
                .stream()
                .map(messages::get);
    }

    @Override
    public Stream<Message> getAllMessagesWithReceiverId(Mailbox.Id receiverId) {
        return indexByReceiverId
                .getOrDefault(receiverId, Collections.emptySet())
                .stream()
                .map(messages::get);
    }
}
