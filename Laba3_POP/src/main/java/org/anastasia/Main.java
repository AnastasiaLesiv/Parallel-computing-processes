package org.anastasia;
public class Main {
   public static void main(String[] args) {
        final int BUFFER_SIZE = 3;
        final int NUMBER_OF_PRODUCTS = 50;
        Manager manager = new Manager(BUFFER_SIZE);

        new Consumer(NUMBER_OF_PRODUCTS, manager).start();
        new Consumer(NUMBER_OF_PRODUCTS, manager).start();

        new Producer(NUMBER_OF_PRODUCTS, manager).start();
        new Producer(NUMBER_OF_PRODUCTS, manager).start();
        new Producer(NUMBER_OF_PRODUCTS, manager).start();

    }
}
