package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.command.PathBuilder;

public abstract class AbstractSolver implements MapSolver {
    protected final Map maze;
    protected final Location start;
    protected final Location end;
    protected final PathBuilder executor = new PathBuilder();

    public AbstractSolver(Map maze, Location start, Location end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }
}
