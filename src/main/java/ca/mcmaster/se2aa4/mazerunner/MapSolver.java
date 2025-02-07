package ca.mcmaster.se2aa4.mazerunner;

public interface MapSolver {
    /**
     * Solve maze and return path through maze.
     *
     * @param maze Maze to solve
     * @return Path that solves the provided maze
     */
    Path solve(Map maze);
}