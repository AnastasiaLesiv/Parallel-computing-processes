package org.anastasia;

class MainThread extends Thread {
    private int threadId;
    private final int TimeSeconds = 5;
    private BreakThread breakThread;

    public MainThread(int id){
        threadId = id;
        breakThread = new BreakThread(TimeSeconds);
    }

    @Override
    public void run() {
        int step = 2;
        long sum = 0;
        long countElement = 0;

        while (!breakThread.getIsBreak().get()) {
            sum += step;
            countElement++;
        }

        System.out.println("Thread " + threadId + ": Sum = " + sum + ", Number of elements: " + countElement);
    }
}
