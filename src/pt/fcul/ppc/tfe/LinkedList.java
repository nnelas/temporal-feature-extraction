package pt.fcul.ppc.tfe;

import java.util.Arrays;

public class LinkedList {
    private final Integer[] linkedList;
    private final int capacity;
    private int currentIndex;

    public LinkedList(int capacity) {
        this.capacity = capacity;
        this.linkedList = new Integer[capacity];
        this.currentIndex = 0;
    }

    public void add(int value) {
        int indexToInsert;
        if (currentIndex < capacity) {
            indexToInsert = currentIndex;
        } else {
            indexToInsert = currentIndex % capacity;
        }
        linkedList[indexToInsert] = value;
        currentIndex += 1;
    }

    public int getAverage() {
        int sum = 0;
        int limit = Math.min(currentIndex, capacity);
        for (int i = 0; i < limit; i++) {;
            sum += linkedList[i];
        }
        return sum / capacity;
    }

    @Override
    public String toString() {
        return "LinkedList{" +
                "linkedList=" + Arrays.toString(linkedList) +
                '}';
    }
}
