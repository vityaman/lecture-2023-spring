package ru.vityaman.demo.mailbox;

import lombok.Value;

@Value
class Mailbox {
    Id id;
    Address publicAddress;

    @Value
    static class Id {
        int value;
    }

    @Value
    static class Address {
        String value;
    }
}
