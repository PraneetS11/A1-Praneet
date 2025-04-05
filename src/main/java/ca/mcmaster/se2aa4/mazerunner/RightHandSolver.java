package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.command.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver extends AbstractSolver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private Location currentPosition;
    private Direction direction;

    public RightHandSolver(Map maze, Location start, Location end) {
        super(maze, start, end);
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
