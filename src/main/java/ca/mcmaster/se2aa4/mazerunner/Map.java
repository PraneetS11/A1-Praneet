package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private static final Logger logger = LogManager.getLogger(Map.class);

    // The maze grid: each inner list represents a row.
    // 'true' means a wall ('#'), 'false' means an open path.
    private final List<List<Boolean>> maze = new ArrayList<>();

    private final Location start;
    private final Location end;

    /**
     * Initialize a Maze from a file path.
     *
     * @param filePath File path of the maze file.
     * @throws Exception If the maze cannot be read, if rows are inconsistent,
     *                   or if no valid start or end Location is found.
     */
    public Map(String filePath) throws Exception {
        logger.debug("Reading the maze from file " + filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int expectedWidth = -1;
        while ((line = reader.readLine()) != null) {
           
            List<Boolean> newLine = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    newLine.add(true);
                } else {
                    newLine.add(false);
                }
            }
            // Set expectedWidth based on the first non-empty row
            if (expectedWidth == -1) {
                expectedWidth = newLine.size();
            } else if (newLine.size() < expectedWidth) {
                // Pad the row with false until it reaches the expected width.
                while (newLine.size() < expectedWidth) {
                    newLine.add(false);
                }
            } else if (newLine.size() > expectedWidth) {
                // Trim the row if it's too long.
                newLine = newLine.subList(0, expectedWidth);
            }
            maze.add(newLine);
        }
        reader.close();

        if (maze.isEmpty()) {
            throw new Exception("Maze file is empty or contains no valid rows.");
        }

        // (Optional) Verify row consistency (should always be true now).
        for (int i = 0; i < maze.size(); i++) {
            if (maze.get(i).size() != expectedWidth) {
                throw new Exception("Inconsistent row lengths in maze file. Expected width: " + expectedWidth +
                        ", but row " + i + " has length " + maze.get(i).size());
            }
        }

        start = findStart();
        end = findEnd();
    }

    /**
     * Find start Location of the maze.
     *
     * @return The start Location.
     * @throws Exception If no valid start Location exists.
     */
    private Location findStart() throws Exception {
        logger.info("Finding start location...");

        for (int y = 0; y < maze.size(); y++) {
            Location loc = new Location(0, y);
            logger.info("Checking location: " + loc);
            
            if (!isWall(loc)) {
                logger.info("Start location found: " + loc);
                return loc;
            }
        }

        logger.error("Invalid maze (no start Location available)");
        throw new Exception("Invalid maze (no start Location available)");
    }

    /**
     * Find end Location of the maze.
     *
     * @return The end Location.
     * @throws Exception If no valid end Location exists.
     */
    private Location findEnd() throws Exception {
        logger.info("Finding end location...");

        for (int y = 0; y < maze.size(); y++) {
            Location loc = new Location(maze.get(0).size() - 1, y);
            logger.debug("Checking location: " + loc);

            if (!isWall(loc)) {
                logger.info("End location found: " + loc);
                return loc;
            }
        }

        logger.error("Invalid maze (no end Location available)");
        throw new Exception("Invalid maze (no end Location available)");
    }

    /**
     * Check if Location of Maze is a wall.
     *
     * @param loc The Location to check.
     * @return True if Location is a wall, false otherwise.
     */
    public Boolean isWall(Location loc) {
        return maze.get(loc.y()).get(loc.x());
    }

    /**
     * Get start Location.
     *
     * @return Start Location.
     */
    public Location getStart() {
        return start;
    }

    /**
     * Get end Location.
     *
     * @return End Location.
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Get horizontal (X) size of Maze.
     *
     * @return Horizontal size.
     */
    public int getSizeX() {
        return maze.get(0).size();
    }

    /**
     * Get vertical (Y) size of Maze.
     *
     * @return Vertical size.
     */
    public int getSizeY() {
        return maze.size();
    }
    public Boolean isPathValid(Path path) {
        return isPathValidDirection(path, getStart(), Direction.RIGHT, getEnd()) ||
               isPathValidDirection(path, getEnd(), Direction.LEFT, getStart());
    }
    
    /**
     * Validates if a given path correctly moves from start to end.
     *
     * @param path The path to check.
     * @param startPos The initial position in the maze.
     * @param startDir The direction the path starts in.
     * @param endPos The final position in the maze.
     * @return True if the path is valid, otherwise false.
     */
    private Boolean isPathValidDirection(Path path, Location startPos, Direction startDir, Location endPos) {
        Location current = startPos;
        Direction currentDir = startDir;
    
        for (char step : path.getSteps()) {
            switch (step) {
                case 'F' -> {
                    current = current.move(currentDir);
    
                    // Check if out of bounds
                    if (current.x() < 0 || current.y() < 0 || current.x() >= getSizeX() || current.y() >= getSizeY()) {
                        return false;
                    }
                    // Check if it's a wall
                    if (isWall(current)) {
                        return false;
                    }
                }
                case 'R' -> currentDir = currentDir.turnRight();
                case 'L' -> currentDir = currentDir.turnLeft();
            }
            logger.debug("Current Position: " + current);
        }
    
        return current.equals(endPos);
    }
    
}
