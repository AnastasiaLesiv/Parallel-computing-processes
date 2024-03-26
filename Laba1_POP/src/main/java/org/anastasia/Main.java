package org.anastasia;


public class Main {

    private static final int threadsNum = 5;
    private static final int timeSeconds = 15;

    public static void main(String[] args) {
        BreakThread breakThread = new BreakThread(timeSeconds);
        breakThread.start();

        Thread[] threads = new Thread[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            threads[i] = new MainThread(i);
            threads[i].start();
        }

    }
}