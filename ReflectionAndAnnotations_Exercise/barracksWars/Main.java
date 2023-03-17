package ReflectionAndAnnotations_Exercise.barracksWars;

import ReflectionAndAnnotations_Exercise.barracksWars.interfaces.Repository;
import ReflectionAndAnnotations_Exercise.barracksWars.interfaces.Runnable;
import ReflectionAndAnnotations_Exercise.barracksWars.interfaces.UnitFactory;
import ReflectionAndAnnotations_Exercise.barracksWars.core.Engine;
import ReflectionAndAnnotations_Exercise.barracksWars.core.factories.UnitFactoryImpl;
import ReflectionAndAnnotations_Exercise.barracksWars.data.UnitRepository;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
