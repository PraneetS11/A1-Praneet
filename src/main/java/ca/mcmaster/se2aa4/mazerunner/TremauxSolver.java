package ca.mcmaster.se2aa4.mazerunner;

public class TremauxSolver implements MapSolver {
    private final Map maze;
    private final Location start;
    private final Location end;

    public TremauxSolver(Map maze, Location start, Location end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    @Override
    public Path solve(Map maze) {
        System.out.println("Tremaux solver is not implemented yet.");
        return new Path(); // Empty placeholder
    }
}
