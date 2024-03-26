package org.anastasia;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;

public class Manager {
    private final Semaphore buffer;
    private final Queue<String> storage;

    public Manager(int bufferSize) {
        this.buffer = new Semaphore(bufferSize);
        this.storage = new ArrayBlockingQueue<>(bufferSize);
    }

    public Semaphore getBuffer() {
        return buffer;
    }

    public Queue<String> getStorage() {
        return storage;
    }

    public void addItemToStorage(String item) {
        storage.add(item);
    }

    public boolean isFull() {
        return storage.size() == buffer.availablePermits();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }
}