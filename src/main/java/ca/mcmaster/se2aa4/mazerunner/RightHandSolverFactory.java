package ca.mcmaster.se2aa4.mazerunner;

public class RightHandSolverFactory implements SolverFactory {
    @Override
    public MapSolver createSolver(Map map, Location start, Location end) {
        return new RightHandSolver(map, start, end);
    }
}
