package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    public static int generateId() {
        return idCounter.getAndIncrement();
    }
}