package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("** Starting Maze Runner");
        logger.info("Logger is working!");

        // Print raw arguments for debugging
        logger.info("Arguments received:");
        for (String arg : args) {
            logger.info(arg);
        }

        // Set up CLI options
        Options options = getParserOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            // Ensure the required -i option is provided
            if (!cmd.hasOption("i")) {
                logger.error("/!\\ Missing required -i argument for input file");
                return;
            }

            // Retrieve the file path using the "i" option
            String filePath = cmd.getOptionValue("i");
            logger.info("Extracted file path: " + filePath);
            logger.info("**** Reading the maze from file: " + filePath);

            // Create a Map object from the file path
            Map map = new Map(filePath);
            logger.info("Maze successfully loaded. Start: " + map.getStart() + ", End: " + map.getEnd());

            // Set the initial movement direction (default to RIGHT)
            Direction initialDirection = Direction.RIGHT;

            // Initialize the solver with the map and its start location
            logger.info("Initializing solver...");
            Solver solver = new Solver(map, map.getStart(), initialDirection);

            // Solve the maze
            logger.info("Calling solver.solve()...");
            Path solutionPath = solver.solve();

            // Output the solution path in factorized form
            if (solutionPath != null) {
                logger.info("**** Computing path");
                logger.info("Solution Path: " + solutionPath);
            } else {
                logger.warn("PATH NOT COMPUTED");
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\", e);
            e.printStackTrace();
        }

        logger.info("** End of MazeRunner");
    }

    /**
     * Defines and returns the CLI options.
     *
     * @return CLI parser options.
     */
    private static Options getParserOptions() {
        Options options = new Options();

        // Create an option with short name "i" and long name "input"
        Option fileOption = new Option("i", "input", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        return options;
    }
}
