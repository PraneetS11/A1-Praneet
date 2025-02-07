package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solver implements MapSolver {
    private static final Logger logger = LogManager.getLogger(Solver.class);

    private final Map maze;
    private Location currentPosition;
    private Direction direction;

    /**
     * Initializes the solver with the maze.
     *
     * @param maze The maze map to be solved.
     */
    public Solver(Map maze) {
        this.maze = maze;
        this.currentPosition = maze.getStart();
        this.direction = Direction.RIGHT; // Default start facing right
        logger.info("Solver initialized with start position: " + currentPosition + " and initial direction: " + direction);
    }

    /**
     * Solves the maze using the right-hand rule.
     *
     * @return A Path object containing the movement instructions.
     */
    @Override
    public Path solve(Map maze) {  // <--- FIX: Ensure method signature matches MapSolver
        logger.info("Solving the maze using Right-Hand Rule...");
        Path path = new Path();

        while (!currentPosition.equals(this.maze.getEnd())) {
            logger.debug("Current Position: " + currentPosition + " | Facing: " + direction);

            if (!this.maze.isWall(currentPosition.move(direction.turnRight()))) {
                // Turn right and move forward if possible
                direction = direction.turnRight();
                path.addStep('R');
                currentPosition = currentPosition.move(direction);
                path.addStep('F');
                logger.debug("Turned right and moved forward to " + currentPosition);
            } else if (!this.maze.isWall(currentPosition.move(direction))) {
                // Move forward if possible
                currentPosition = currentPosition.move(direction);
                path.addStep('F');
                logger.debug("Moved forward to " + currentPosition);
            } else if (!this.maze.isWall(currentPosition.move(direction.turnLeft()))) {
                // Turn left and move forward if possible
                direction = direction.turnLeft();
                path.addStep('L');
                currentPosition = currentPosition.move(direction);
                path.addStep('F');
                logger.debug("Turned left and moved forward to " + currentPosition);
            } else {
                // Dead-end, turn around (180 degrees)
                direction = direction.turnRight().turnRight();
                path.addStep('R');
                path.addStep('R');
                logger.debug("Dead-end! Turned around. Now facing " + direction);
            }

            logger.debug("Current Path: " + path.getFactorizedForm());
        }

        logger.info("Maze solved! Final Path: " + path.getFactorizedForm());
        return path;
    }
}
