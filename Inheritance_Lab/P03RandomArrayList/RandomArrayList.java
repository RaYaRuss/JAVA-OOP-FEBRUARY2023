package Inheritance_Lab.P03RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList<T> extends ArrayList<T> {
    private Random random;

    public RandomArrayList() {
        super(); // извиква се празния конструктор на арейлист/ базов конструктор

        this.random = new Random();
    }

    public Object getRandomElement() {

        int index = random.nextInt(super.size());
        // диапазонът с валидни индекси от нашата колекция:
        // [0, size - 1]

       // int index = rand % (super.size() - 1);

        T result = super.get(index);
        super.remove(index);
        return result;
    }
}
