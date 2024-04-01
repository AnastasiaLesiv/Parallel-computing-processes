package org.anastasia;

public class Consumer extends Thread {
    private final int numberOfProducts;
    private final Manager manager;

    public Consumer(int numberOfProducts, Manager manager) {
        this.numberOfProducts = numberOfProducts;
        this.manager = manager;
    }

    @Override
    public void run() {
        for (int i = 0; i < numberOfProducts; i++) {
            try {
                String item = manager.consumeItem();
                System.out.println("Consumed item " + item);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
