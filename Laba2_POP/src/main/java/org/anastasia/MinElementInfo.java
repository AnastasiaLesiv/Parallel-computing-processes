package org.anastasia;

public class MinElementInfo {
    private int value;
    private int index;

    public MinElementInfo(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}