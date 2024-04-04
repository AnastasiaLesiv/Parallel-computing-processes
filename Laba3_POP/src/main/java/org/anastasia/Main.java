package org.anastasia;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

 class BlockingQueueUsage {
    public static void main(String[] args) {
        int NUM_OF_PRODUCTS = 10;
        int CAPACITY = 3;
        int PRODUCERS = 4;
        int CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = 100;
        int poisonPillPerProducer = CONSUMERS / PRODUCERS;
        int mod = CONSUMERS % PRODUCERS;
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(CAPACITY);

        for (int i = 0; i < PRODUCERS; i++) {
            if(i < PRODUCERS - 1) {
                int numOfProducts = NUM_OF_PRODUCTS / PRODUCERS;
                int firstProduct = numOfProducts * i;
                new Thread(new Producer(queue, numOfProducts,firstProduct, poisonPill, poisonPillPerProducer)).start();
            }
            else{
                int numOfProducts = NUM_OF_PRODUCTS / PRODUCERS + NUM_OF_PRODUCTS % PRODUCERS;
                int firstProduct = NUM_OF_PRODUCTS / PRODUCERS * i;
                new Thread(new Producer(queue, numOfProducts,firstProduct, poisonPill, poisonPillPerProducer+mod)).start();
            }
        }

        for (int j = 0; j < CONSUMERS; j++) {
            new Thread(new Consumer(queue, poisonPill)).start();
        }



    }
}
