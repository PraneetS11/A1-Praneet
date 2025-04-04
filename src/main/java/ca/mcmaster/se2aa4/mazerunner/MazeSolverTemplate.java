package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolverTemplate implements MapSolver {
    protected Map maze;
    protected Location currentPosition;
    protected Direction direction;
    protected Path path;

    public MazeSolverTemplate(Map maze) {
        this.maze = maze;
        this.currentPosition = maze.getStart();
        this.direction = Direction.RIGHT;
        this.path = new Path();
    }

    @Override
    public Path solve(Map maze) {
        while (!currentPosition.equals(this.maze.getEnd())) {
            step();
        }
        return path;
    }

    protected abstract void step();
}
