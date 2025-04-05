package ca.mcmaster.se2aa4.mazerunner;

public class TremauxSolver extends AbstractSolver {
    public TremauxSolver(Map maze, Location start, Location end) {
        super(maze, start, end);
    }

    @Override
    public Path solve(Map maze) {
        System.out.println("Tremaux solver is not implemented yet.");
        return new Path(); // Empty placeholder
    }
}
