package org.anastasia;

import java.util.concurrent.Semaphore;
public class Main {
    public static void main(String[] args) {
        Semaphore[] forks = new Semaphore[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Semaphore(1);
        }

        Semaphore eatingLimit = new Semaphore(4);

        Philosopher[] philosophers = new Philosopher[5];
        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(forks[i], forks[(i + 1) % forks.length], i + 1, eatingLimit);
            philosophers[i].start();
        }
    }
}
