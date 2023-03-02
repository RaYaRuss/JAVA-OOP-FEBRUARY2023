package WorkingWithAbstraction_Exercise.P05JediGalaxy;

public class Jedi {
    public int move(int rowJedi, int colJedi, Field field) {
        int starsCollected = 0;
        while (rowJedi >= 0 && colJedi < field.getColLength()) {
            if (field.isInBounds(rowJedi, colJedi)) {
                starsCollected += field.getValue(rowJedi, colJedi);
            }
            colJedi++;
            rowJedi--;
        }
        return starsCollected;
    }
}