package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.Path;

public class ForwardCommand implements Command {
    @Override
    public void execute(Path path) {
        path.addStep('F');
    }
}
