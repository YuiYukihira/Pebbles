package Pebbles;

import java.util.Vector;

public abstract class ThreadBag extends Thread implements IBag {
    final Vector<Integer> stones;

    ThreadBag(String name) {
        super(name);
        stones = new Vector<>();
    }

    ThreadBag(String name, Vector<Integer> l) {
        super(name);
        stones = l;
    }

    public void addStone(Integer weight) {
        stones.add(weight);
    }
    
    public synchronized Integer removeStone(int index) {
        return stones.remove(index);
    }

    public int size() {
        return stones.size();
    }

    String showStones() {
        StringBuilder out = new StringBuilder();
        for (int i=0; i < stones.size() - 1; i++) {
            out.append(stones.get(i)).append(", ");

        }
        out.append(stones.get(stones.size()-1));
        return out.toString();
    }
}