package org.anastasia;
import java.util.concurrent.TimeUnit;

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
                manager.getBuffer().release();
                TimeUnit.MILLISECONDS.sleep(300);
                while (manager.isEmpty()) {
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                System.out.println("Consumed item " + manager.getStorage().poll());
                manager.getBuffer().acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}