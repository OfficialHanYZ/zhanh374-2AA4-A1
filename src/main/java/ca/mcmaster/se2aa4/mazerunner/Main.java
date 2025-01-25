package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", true, "Input maze file");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {

            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error("Error parsing command line arguments.", e);
            System.exit(1);
        }

        String inputMazeFile = cmd.getOptionValue("i");
        if (inputMazeFile == null) {
            logger.error("No input file specified. Exiting...");
            System.exit(1);
        }

        logger.info("** Starting Maze Runner");

        try {
            logger.info("**** Reading the maze from file: {}", inputMazeFile);
            BufferedReader reader = new BufferedReader(new FileReader(inputMazeFile));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.trace(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading the maze file /!\\", e);
        }

        Maze maze = new Maze(inputMazeFile);
        Solver solver = new Solver(maze);
        maze.printMaze();
        List<Character> path = solver.explore();
        System.out.println("Path to exit: " + pathToString(path));

        //logger.info("**** Computing path");
        //logger.warn("PATH NOT COMPUTED");
        //logger.info("** End of MazeRunner");
    }

    private static String pathToString(List<Character> path) {
        StringBuilder pathStr = new StringBuilder();
        for (char step : path) {
            pathStr.append(step);
        }
        return pathStr.toString();
    }

}
