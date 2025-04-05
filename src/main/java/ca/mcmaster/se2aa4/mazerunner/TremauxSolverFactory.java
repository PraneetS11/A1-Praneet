package ca.mcmaster.se2aa4.mazerunner;

public class TremauxSolverFactory implements SolverFactory {
    @Override
    public MapSolver createSolver(Map map, Location start, Location end) {
        return new TremauxSolver(map, start, end);
    }
}
