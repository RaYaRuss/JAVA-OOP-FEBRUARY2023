package ReflectionAndAnnotations_Exercise.barracksWars.interfaces;

import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Runnable {
	void run() throws IOException, ExecutionControl.NotImplementedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
