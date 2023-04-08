package robotService.entities.robot;

public class FemaleRobot extends BaseRobot{
    private static final int INITIAL_KG = 7;


    public FemaleRobot(String name, String kind, double price) {

        super(name, kind, INITIAL_KG, price);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 1);
    }
}
