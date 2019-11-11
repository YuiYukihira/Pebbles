package Pebbles;

import java.util.List;

public abstract class Bag {
    protected List<Integer> stones;

    public void addStone(Integer weight) {
        stones.add(weight);
    }

    public Integer removeStone(int index) {
        return stones.remove(index);
    }
}
