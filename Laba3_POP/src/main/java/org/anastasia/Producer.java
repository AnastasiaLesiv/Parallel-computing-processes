package org.anastasia;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {
    private final int numberOfProducts;
    private final Manager manager;

    public Producer(int numberOfProducts, Manager manager) {
        this.numberOfProducts = numberOfProducts;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfProducts; i++) {
            String item = String.valueOf(i);
            try {
                manager.getBuffer().acquire();
                TimeUnit.MILLISECONDS.sleep(100);
                while (manager.isFull()) {
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                System.out.println("Produced item " + item);
                manager.addItemToStorage(item);
                manager.getBuffer().release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}