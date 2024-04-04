package org.anastasia;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private final BlockingQueue<Integer> numbersQueue;
    private final int numOfProducts;
    private final int lastProduct;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    Producer(BlockingQueue<Integer> numbersQueue,int numOfProducts, int lastProduct, int poisonPill, int poisonPillPerProducer) {
        this.numbersQueue = numbersQueue;
        this.numOfProducts = numOfProducts;
        this.lastProduct = lastProduct;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    public void run() {
        try {
            productionOfProducts();
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }
    }

    private void productionOfProducts() throws InterruptedException {
        for (int i = lastProduct; i < lastProduct + numOfProducts; i++) {
            numbersQueue.put(i);
            System.out.println(Thread.currentThread().getName()  + " put " + i);
        }
        for (int j = 0; j < poisonPillPerProducer; j++) {
            numbersQueue.put(poisonPill);
        }
    }
}