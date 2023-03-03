package Encapsulation_Exercise.P06GreedyTimes;

import java.util.List;

public class Bag {
    // hold the items - map?
    private List<Item> items;
    private int capacity;
    // put in bag

    public void putItem(Item item) {
        // is this a valid item?
        // can i store it?


        if (weCanStore(item)) {
            // check if we should update value or create a new one / dali go imame veche
            items.add(item);
        }
    }
    private boolean weCanStore(Item item) {
        if (capacity < getTotalAmount() + item.getAmount()) {
            return false;
        }
        switch (item.getItemType()) {
            case GEM:
                if (getAmount(ItemType.GEM) + item.getAmount() > getAmount(ItemType.GOLD)) {
                    return false;
                }
                break;
            case CASH:
                if (getAmount(ItemType.CASH) + item.getAmount() > getAmount(ItemType.GEM)) {
                    return false;
                }
                break;
        }
        return true;
    }

    public long getTotalAmount() {
        return getAmount(ItemType.GEM) + getAmount(ItemType.CASH) + getAmount(ItemType.GOLD);
    }

    // toString method


    @Override
    public String toString() {
        // print logic;
        return  "";
    }

    public long getAmount(ItemType itemType) {
        return this.items.stream().filter(e -> e.getItemType().equals(itemType))
                .mapToLong(e -> e.getAmount())
                .sum();
    }
}
