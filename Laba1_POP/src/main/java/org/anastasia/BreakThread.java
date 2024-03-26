package org.anastasia;

import java.util.concurrent.atomic.AtomicBoolean;
class BreakThread extends Thread {
    private static AtomicBoolean isBreak = new AtomicBoolean(false);
    private final int seconds;

    public static AtomicBoolean getIsBreak() {
        return isBreak;
    }



    public BreakThread(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isBreak.set(true);
    }
}