package org.anastasia;

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
                manager.addItemToStorage(item);
                System.out.println("Produced item " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}