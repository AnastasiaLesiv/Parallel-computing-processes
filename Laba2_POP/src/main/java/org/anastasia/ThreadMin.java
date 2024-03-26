package org.anastasia;


import org.anastasia.ArrClass;

public class ThreadMin extends Thread {
    private final int startIndex;
    private final int finishIndex;
    private final ArrClass partArray;

    public ThreadMin(int startIndex, int finishIndex, ArrClass partArray){
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.partArray = partArray;
    }
    @Override
    public void run(){
        partArray.incThreadCount();
        partArray.searchMinInPart(this.startIndex, this.finishIndex);
    }
}
