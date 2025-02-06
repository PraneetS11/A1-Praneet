package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Solver {
    private static final Logger logger = LogManager.getLogger(Solver.class);

    private final Map mazeMap;
    private Location currentPosition;
    private final Direction direction;

    /**
     * Initializes the solver with the map, starting location, and initial direction.
     *
     * @param mazeMap       The maze map to be solved.
     * @param start         The starting location of the solver.
     * @param direction     The initial direction of movement.
     */
    public Solver(Map mazeMap, Location start, Direction direction) {
        this.mazeMap = mazeMap;
        this.currentPosition = start;
        this.direction = direction;
        logger.info("Solver initialized with start: " + start + " and direction: " + direction);
    }

    /**
     * Solves the maze by moving forward until it hits a wall or reaches the End.
     *
     * @return A Path object containing the movement instructions.
     */
    public Path solve() {
        logger.info("Solving the maze...");
        Path path = new Path();

        while (!currentPosition.equals(mazeMap.getEnd())) {
            Location nextPosition = currentPosition.move(direction);

            if (mazeMap.isWall(nextPosition)) {
                logger.info("Hit a wall at " + nextPosition + ". Stopping.");
                break;
            }

            currentPosition = nextPosition;
            path.addStep('F');
        }

        logger.info("Maze solved. Path: " + path);
        return path;
    }
}
