package Pebbles;

import java.util.Vector;

public class Bag implements IBag{
    private final Vector<Integer> stones;

    Bag() {
        stones = new Vector<>();
    }

    Bag(Vector<Integer> l) {
        stones = l;
    }

    public synchronized void addStone(Integer weight) {
        stones.add(weight);
    }

    public synchronized Integer removeStone(int index) {
        return stones.remove(index);
    }

    public synchronized int size() {
        return stones.size();
    }
}