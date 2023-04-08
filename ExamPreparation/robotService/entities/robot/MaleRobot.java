package robotService.entities.robot;

public class MaleRobot extends BaseRobot{
    private static final int INITIAL_KG = 9;

    public MaleRobot(String name, String kind, double price) {
        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 3);
    }
}
