package ru.vityaman.demo.mailbox.model;

import lombok.Value;

@Value
public class Mailbox {
    Id id;
    Address publicAddress;

    @Value
    static public class Id {
        int value;
    }

    @Value
    static public class Address {
        String value;
    }
}
