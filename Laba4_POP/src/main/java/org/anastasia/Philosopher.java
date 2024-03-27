package org.anastasia;
import java.util.concurrent.Semaphore;
class Philosopher extends Thread {
    private final Semaphore leftFork;
    private final Semaphore rightFork;
    private final int id;
    private final Semaphore eatingLimit;

    public Philosopher(Semaphore leftFork, Semaphore rightFork, int id, Semaphore eatingLimit) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.id = id;
        this.eatingLimit = eatingLimit;
    }

    public void run() {
        while (true) {
            think();

            try {
                eatingLimit.acquire();

                leftFork.acquire();
                System.out.println("Philosopher " + id + " took left fork.");

                rightFork.acquire();
                System.out.println("Philosopher " + id + " took right fork.");

                eat();

                rightFork.release();
                System.out.println("Philosopher " + id + " put down right fork.");

                leftFork.release();
                System.out.println("Philosopher " + id + " put down left fork.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                eatingLimit.release();
            }
        }
    }
    private void think() {
        System.out.println("Philosopher " + id + " started thinking.");

        // Мислити
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Philosopher " + id + " finished thinking.");
    }

    private void eat() {
        System.out.println("Philosopher " + id + " started eating.");

        // Їсти
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Philosopher " + id + " finished eating.");
    }
}