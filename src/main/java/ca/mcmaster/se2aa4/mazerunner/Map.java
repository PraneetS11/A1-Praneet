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
            // Skip truly empty lines (length 0) but not lines with spaces.
            if (line.length() == 0) {
                continue;
            }
            List<Boolean> newLine = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    newLine.add(true);
                } else if (line.charAt(idx) == ' ') {
                    newLine.add(false);
                } else {
                    // For any other character, treat it as an open path.
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
        for (int i = 0; i < maze.size(); i++) {
            Location loc = new Location(0, i);
            if (!isWall(loc)) {
                return loc;
            }
            // Alternatively, check the right boundary as a potential entry.
            if (!maze.get(i).get(maze.get(i).size() - 1)) {
                return new Location(maze.get(i).size() - 1, i);
            }
        }
        throw new Exception("Invalid maze (no start Location available)");
    }

    /**
     * Find end Location of the maze.
     *
     * @return The end Location.
     * @throws Exception If no valid end Location exists.
     */
    private Location findEnd() throws Exception {
        for (int i = 0; i < maze.size(); i++) {
            Location loc = new Location(maze.get(0).size() - 1, i);
            if (!isWall(loc)) {
                return loc;
            }
        }
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
}
