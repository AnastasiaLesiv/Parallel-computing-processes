package org.anastasia;

public class Main {
    public static void main(String[] args) {
        int countElements = 75;
        int countThreads = 7;

        ArrClass arrClass = new ArrClass(countElements, countThreads);
        MinElementInfo minElementInfo = arrClass.threadMin();

        int minValue = minElementInfo.getValue();
        int minIndex = minElementInfo.getIndex();

        System.out.println("Minimal element: " + minValue);
        System.out.println("The index of the minimum element: " + minIndex);
    }
}
