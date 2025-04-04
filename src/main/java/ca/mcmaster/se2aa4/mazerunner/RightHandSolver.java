package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.command.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandSolver extends MazeSolverTemplate {
    private static final Logger logger = LogManager.getLogger(RightHandSolver.class);
    private final CommandExecutor executor = new CommandExecutor();

    public RightHandSolver(Map maze) {
        super(maze);
        logger.info("Solver initialized with start position: " + currentPosition + " and initial direction: " + direction);
    }

    @Override
    protected void step() {
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
