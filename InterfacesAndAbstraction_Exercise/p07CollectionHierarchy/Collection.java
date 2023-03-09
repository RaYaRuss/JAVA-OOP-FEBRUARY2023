package InterfacesAndAbstraction_Exercise.p07CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    private int maxSize;
    protected List<String> items;

    public Collection() {
    items = new ArrayList<>();
    maxSize = 100;

    }
}
