package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

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

            // Check if the -p flag is provided for path verification
            if (cmd.hasOption("p")) {
                logger.info("Validating path...");
                String pathInput = cmd.getOptionValue("p");

                // Expand factorized path if necessary
                Path path = new Path(Path.expandFactorizedStringPath(pathInput));

                if (map.isPathValid(path)) {
                    logger.info("Correct path");
                    System.out.println("Correct path");
                } else {
                    logger.info("Incorrect path");
                    System.out.println("Incorrect path");
                }
            } else {
                // Solve the maze normally if no -p flag is provided
                logger.info("Initializing solver...");

                // Use MapSolver interface instead of direct Solver instantiation
                MapSolver solver = new Solver(map); 

                // Solve the maze
                logger.info("Calling solver.solve()...");
                Path solutionPath = solver.solve(map);

                // Output the solution path
                if (solutionPath != null) {
                    logger.info("**** Computing path");
                    String canonicalSolution = solutionPath.getFactorizedForm();
                    logger.info("Solution Path: " + canonicalSolution);
                    System.out.println(canonicalSolution);
                } else {
                    logger.warn("PATH NOT COMPUTED");
                }
            }
        } catch (Exception e) {
            System.err.println("MazeSolver failed. Reason: " + e.getMessage());
            logger.error("MazeSolver failed. Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
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

        // Add -p flag for path validation
        options.addOption(new Option("p", "path", true, "Path to be verified in maze"));

        // Add optional solver selection flag
        options.addOption(new Option("s", "solver", true, "Solver algorithm to use (default: basic)"));

        return options;
    }

    /**
     * Determines which solver to use based on user input.
     *
     * @param map The maze to solve.
     * @param cmd The command line arguments.
     * @return An instance of the selected solver.
     */
    private static MapSolver getSolver(Map map, CommandLine cmd) {
        String solverType = cmd.getOptionValue("s", "RHS"); // Default solver to "RHS"
    
        switch (solverType.toLowerCase()) {
            case "rhs":
                logger.info("Using Right-Hand Solver (RHS)");
                return new Solver(map); // Default solver
            default:
                logger.warn("Unknown solver type provided: " + solverType + ". Using default RHS solver.");
                return new Solver(map); // Ensure a solver is always returned
        }
    }
}
