package org.anastasia;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Manager {
    private final BlockingQueue<String> storage;

    public Manager(int bufferSize) {
        this.storage = new ArrayBlockingQueue<>(bufferSize);
    }

    public BlockingQueue<String> getStorage() {
        return storage;
    }

    public void addItemToStorage(String item) throws InterruptedException {
        storage.put(item);
    }

    public String consumeItem() throws InterruptedException {
        return storage.take();
    }
}
