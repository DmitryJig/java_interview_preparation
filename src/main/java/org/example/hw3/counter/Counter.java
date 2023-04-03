package org.example.hw3.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Реализуем потокобезопасный счетчик
 */
public class Counter {
    private final Lock lock = new ReentrantLock();
    private Long number = 0L;

    public Long getNumber() {
        return number;
    }

    public void increment() {
        lock.lock();
        try {
            number++;
        } finally {
            lock.unlock();
        }
    }
}
