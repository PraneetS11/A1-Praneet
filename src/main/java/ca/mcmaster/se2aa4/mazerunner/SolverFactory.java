package ca.mcmaster.se2aa4.mazerunner;

public interface SolverFactory {
    MapSolver createSolver(Map map, Location start, Location end);
}
