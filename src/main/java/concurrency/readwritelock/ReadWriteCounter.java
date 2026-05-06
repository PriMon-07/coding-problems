package concurrency.readwritelock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import static java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReadWriteCounter {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReadLock readLock = rwLock.readLock();
    private final WriteLock writeLock = rwLock.writeLock();

    private int value = 0;

    // Read operation - multiple threads can read simultaneously
    public int getValue() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + value);
            return value;
        } finally {
            readLock.unlock();
        }
    }

    // Write operation - exclusive access
    public void setValue(int newValue) {
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + value + " -> " + newValue);
            value = newValue;
            Thread.sleep(1000); // Simulate slow write
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    // Increment - needs write lock
    public void increment() {
        writeLock.lock();
        try {
            value++;
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}
