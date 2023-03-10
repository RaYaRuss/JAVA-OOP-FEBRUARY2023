package Polymorphism_Exercise.word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface commandInterface = new ExtendedCommandInterface(text);
        commandInterface.init();
        return commandInterface;
    }
}
