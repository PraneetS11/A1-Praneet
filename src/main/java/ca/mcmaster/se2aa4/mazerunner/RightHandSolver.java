package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements MapSolver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private final Map maze;
    private final Location start;
    private final Location end;
    private final PathBuilder executor = new PathBuilder();
    private Location currentPosition;
    private Direction direction;

    public RightHandSolver(Map maze, Location start, Location end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
        this.currentPosition = start;
        this.direction = Direction.RIGHT;
        logger.info("RightHandSolver initialized with start=" + start + " end=" + end);
    }

    @Override
    public Path solve(Map maze) {
        Path path = new Path();
        while (!currentPosition.equals(end)) {
            step(path);
        }
        return path;
    }

    private void step(Path path) {
        logger.debug("At " + currentPosition + " facing " + direction);

        if (!maze.isWall(currentPosition.move(direction.turnRight()))) {
            direction = direction.turnRight();
            executor.add(new RightCommand());
            currentPosition = currentPosition.move(direction);
            executor.add(new ForwardCommand());
        } else if (!maze.isWall(currentPosition.move(direction))) {
            currentPosition = currentPosition.move(direction);
            executor.add(new ForwardCommand());
        } else if (!maze.isWall(currentPosition.move(direction.turnLeft()))) {
            direction = direction.turnLeft();
            executor.add(new LeftCommand());
            currentPosition = currentPosition.move(direction);
            executor.add(new ForwardCommand());
        } else {
            direction = direction.turnAround();
            executor.add(new RightCommand());
            executor.add(new RightCommand());
        }

        executor.run(path);
        logger.debug("Current Path: " + path.getFactorizedForm());
    }
}
