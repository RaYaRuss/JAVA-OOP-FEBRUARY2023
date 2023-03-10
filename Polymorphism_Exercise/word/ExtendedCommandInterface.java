package Polymorphism_Exercise.word;

import java.util.List;

public class ExtendedCommandInterface extends CommandImpl{

    private Clipboard clipboard;

    public ExtendedCommandInterface(StringBuilder text) {
        super(text);
        clipboard = new Clipboard();
    }

    @Override
    protected List<Command> initCommands() {
        List<Command> commands = super.initCommands();



        commands.add(new Command("cut", new CutTransform(clipboard)));
        commands.add(new Command("paste", new PasteTransform(clipboard)));

        return commands;
    }
}
