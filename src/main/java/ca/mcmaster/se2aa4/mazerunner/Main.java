package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");

        Options options = getParserOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("/!\\ Missing required -i argument for input file");
                return;
            }

            String filePath = cmd.getOptionValue("i");
            logger.info("Extracted file path: " + filePath);
            logger.info("**** Reading the maze from file: " + filePath);

            Map map = new Map(filePath);
            logger.info("Maze successfully loaded. Start: " + map.getStart() + ", End: " + map.getEnd());

            if (cmd.hasOption("p")) {
                logger.info("Validating path...");
                String pathInput = cmd.getOptionValue("p");
                Path path = new Path(Path.expandFactorizedStringPath(pathInput));

                if (map.isPathValid(path)) {
                    logger.info("Correct path");
                    System.out.println("correct path");
                } else {
                    logger.info("Incorrect path");
                    System.out.println("incorrect path");
                }
            } else {
                String method = cmd.getOptionValue("method", "rhs");
                logger.info("Selected solving method: " + method);
                MapSolver solver = MazeSolverFactory.createSolver(method, map);

                logger.info("Calling solver.solve()...");
                Path solutionPath = solver.solve(map);

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

    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", "input", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", "path", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Path computation method (rhs, tremaaux, etc.)"));

        return options;
    }
}