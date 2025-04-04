package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver implements MapSolver {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private final Map maze;
    private Location currentPosition;
    private Direction direction;
    private final Path path;
    private final CommandExecutor executor;

    public RightHandSolver(Map maze) {
        this.maze = maze;
        this.currentPosition = maze.getStart();
        this.direction = Direction.RIGHT;
        this.path = new Path();
        this.executor = new CommandExecutor();
        logger.info("Solver initialized with start position: " + currentPosition + " and initial direction: " + direction);
    }

    @Override
    public Path solve(Map maze) {
        while (!currentPosition.equals(this.maze.getEnd())) {
            step();
        }
        return path;
    }

    private void step() {
        logger.debug("Current Position: " + currentPosition + " | Facing: " + direction);

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
