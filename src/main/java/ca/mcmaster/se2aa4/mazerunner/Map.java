package ca.mcmaster.se2aa4.mazerunner;
import ca.mcmaster.se2aa4.mazerunner.Location;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private static final Logger logger = LogManager.getLogger(Map.class);

    private final boolean[][] mazeGrid;
    private final int width;
    private final int height;
    private final Location start;
    private final Location exit;

    /**
     * Loads a maze from the given file and initializes its structure.
     *
     * @param filePath The path to the maze file.
     * @throws IOException If an error occurs while reading the file.
     */
    public Map(String filePath) throws IOException {
        List<String> mazeLines = readMazeFile(filePath);
        this.height = mazeLines.size();
        this.width = mazeLines.get(0).length();
        this.mazeGrid = parseMaze(mazeLines);

        this.start = locateEntry();
        this.exit = locateExit();
        
        logger.info("Maze loaded successfully - Start: " + start + ", Exit: " + exit);
    }

    /**
     * Reads the maze file line by line.
     *
     * @param filePath Path to the maze file.
     * @return List of maze rows as strings.
     * @throws IOException If the file cannot be read.
     */
    private List<String> readMazeFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        if (lines.isEmpty()) {
            throw new IOException("Maze file is empty.");
        }
        return lines;
    }

    /**
     * Converts the raw maze text into a boolean grid representation.
     *
     * @param mazeLines List of strings representing the maze.
     * @return A boolean 2D array where true represents walls and false represents open spaces.
     */
    private boolean[][] parseMaze(List<String> mazeLines) {
        boolean[][] grid = new boolean[height][width];
        for (int y = 0; y < height; y++) {
            char[] rowChars = mazeLines.get(y).toCharArray();
            for (int x = 0; x < width; x++) {
                grid[y][x] = (rowChars[x] == '#'); // Walls are true, paths are false
            }
        }
        return grid;
    }

    /**
     * Identifies the entry point of the maze.
     *
     * @return The starting location of the maze.
     * @throws IllegalStateException If no valid entry point is found.
     */
    private Location locateEntry() {
        for (int y = 0; y < height; y++) {
            if (!mazeGrid[y][0]) return new Location(0, y); // Left boundary
            if (!mazeGrid[y][width - 1]) return new Location(width - 1, y); // Right boundary
        }
        throw new IllegalStateException("No valid maze entry found.");
    }

    /**
     * Identifies the exit point of the maze.
     *
     * @return The exit location of the maze.
     * @throws IllegalStateException If no valid exit point is found.
     */
    private Location locateExit() {
        for (int y = 0; y < height; y++) {
            if (!mazeGrid[y][0] && !start.equals(new Location(0, y))) return new Location(0, y);
            if (!mazeGrid[y][width - 1] && !start.equals(new Location(width - 1, y))) return new Location(width - 1, y);
        }
        throw new IllegalStateException("No valid maze exit found.");
    }

    /**
     * Checks if a given location is within the maze bounds.
     *
     * @param loc The location to validate.
     * @return True if within bounds, false otherwise.
     */
    public boolean isWithinBounds(Location loc) {
        return loc.y() >= 0 && loc.y() < height && loc.x() >= 0 && loc.x() < width;
    }

    /**
     * Checks if a given location is a wall.
     *
     * @param loc The location to check.
     * @return True if it is a wall, false if it's a passage.
     */
    public boolean isWall(Location loc) {
        return isWithinBounds(loc) && mazeGrid[loc.y()][loc.x()];
    }

    /**
     * Retrieves the start location.
     *
     * @return The start position of the maze.
     */
    public Location getStartLocation() {
        return start;
    }

    /**
     * Retrieves the exit location.
     *
     * @return The exit position of the maze.
     */
    public Location getExitLocation() {
        return exit;
    }

    /**
     * Retrieves the maze width.
     *
     * @return The width of the maze.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Retrieves the maze height.
     *
     * @return The height of the maze.
     */
    public int getHeight() {
        return height;
    }
}
