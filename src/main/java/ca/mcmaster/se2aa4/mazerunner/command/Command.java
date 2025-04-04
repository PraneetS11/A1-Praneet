package ca.mcmaster.se2aa4.mazerunner.command;

import ca.mcmaster.se2aa4.mazerunner.Path;

public interface Command {
    void execute(Path path);
}
