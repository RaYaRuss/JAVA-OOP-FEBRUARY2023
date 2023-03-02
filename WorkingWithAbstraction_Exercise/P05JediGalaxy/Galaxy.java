package WorkingWithAbstraction_Exercise.P05JediGalaxy;

public class Galaxy {
    private Field field;
    private Jedi jedi;

    private EvilPower evilPower;

    public Galaxy(Field field) {
        this.field = field;
        jedi = new Jedi();
        evilPower = new EvilPower();
    }

    public int moveJedi(int rowJedi, int colJedi) {
       return jedi.move(rowJedi, colJedi, field);
    }

    public void moveEvil(int rowEvil, int colEvil) {
        evilPower.move(rowEvil, colEvil, field);
    }
}
