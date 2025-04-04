package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.Path;
import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private final List<Command> commands = new ArrayList<>();

    public void add(Command command) {
        commands.add(command);
    }

    public void run(Path path) {
        for (Command command : commands) {
            command.execute(path);
        }
        commands.clear();
    }
}
