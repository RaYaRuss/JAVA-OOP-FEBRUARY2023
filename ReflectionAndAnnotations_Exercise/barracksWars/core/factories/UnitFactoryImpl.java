package ReflectionAndAnnotations_Exercise.barracksWars.core.factories;

import ReflectionAndAnnotations_Exercise.barracksWars.interfaces.Unit;
import ReflectionAndAnnotations_Exercise.barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"ReflectionAndAnnotations_Exercise.barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		try {
			Class clazz = Class.forName("ReflectionAndAnnotations_Exercise.barracksWars.models.units." + unitType);
			Constructor<Unit> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			return constructor.newInstance();
		}  catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
				  IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}