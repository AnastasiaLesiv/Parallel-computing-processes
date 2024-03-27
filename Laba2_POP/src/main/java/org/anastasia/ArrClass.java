package org.anastasia;

import java.util.Random;

public class ArrClass {
    private final int ARRAY_SIZE;
    private final int NUM_THREADS;
    public final int[] arrayElem;
    Random rand = new Random();

    public ArrClass(int countElements, int countThreads) {
        this.ARRAY_SIZE = countElements;
        this.NUM_THREADS = countThreads;
        arrayElem = new int[countElements];
        int randomNegative = rand.nextInt(ARRAY_SIZE - 1);
        System.out.print("Initialized array: ");
        for (int i = 0; i < countElements; i++) {
            if(i % 9 == 0)
                System.out.println();
            if(i == randomNegative){
                arrayElem[i] = rand.nextInt(100) * (-1);
                System.out.print( "[" + i +"] - " +arrayElem[i] + "  ");
            }
            else{
                arrayElem[i] = rand.nextInt(100);
                System.out.print( "[" + i +"] - "  +arrayElem[i] + "  ");
            }
        }
        System.out.println();
    }

    private MinElementInfo minElementInfo = new MinElementInfo(Integer.MAX_VALUE, 0);

    public synchronized void searchMinInPart(int startIndex, int finishIndex){
        int minElement = arrayElem[startIndex];
        int indexMin = startIndex;
        for (int i = startIndex + 1; i < finishIndex; i++) {
            if(arrayElem[i] < minElement){
                minElement = arrayElem[i];
                indexMin = i;
            }
        }
        checkAndUpdateValue(minElement, indexMin);
    }

    synchronized private void checkAndUpdateValue(int minElement, int index) {
        MinElementInfo currentMin = getMinElementInfo();
        if (minElement < currentMin.getValue()) {
            setMinElementInfo(new MinElementInfo(minElement, index));
        }
    }

    private MinElementInfo getMinElementInfo() {
        return minElementInfo;
    }

    private synchronized void setMinElementInfo(MinElementInfo info) {
        minElementInfo = info;
    }

    public synchronized void incThreadCount() {
        if (++threadCount == NUM_THREADS) {
            notify();
        }
    }

    private int threadCount = 0;

    public MinElementInfo threadMin() {
        ThreadMin[] threadMin = new ThreadMin[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            final int startIndex = i * ARRAY_SIZE / NUM_THREADS;
            final int endIndex = (i == NUM_THREADS - 1) ? ARRAY_SIZE : (i + 1) * ARRAY_SIZE / NUM_THREADS;
            threadMin[i] = new ThreadMin(startIndex, endIndex, this);
            threadMin[i].start();
        }
        return getMinElementInfo();
    }
}
