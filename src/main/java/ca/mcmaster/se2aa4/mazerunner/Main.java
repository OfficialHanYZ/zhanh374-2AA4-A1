/* Han Yang Zhang, 400520954, Februaru 3rd 2025
 *
 * This program allows the user to generate an acceptable path through a provided text maze with two openings on the east and west sides. 
 * Otherwise the user can also use the -p flag to check if a path can work for a given maze.
 */

package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* Main function
 *
 * Description: Takes in user input and calls upon other classes to run program
 * Output/Return Value: Prints out the canonical and factorized form of the path, or if the path is correct or not.
 */
public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("i", true, "Input maze file");
        options.addOption("p", true, "Check input path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.exit(1);
        }

        String inputMazeFile = cmd.getOptionValue("i");
        if (inputMazeFile == null) {
            System.exit(1);
        }

        try{
            String direction = "west"; //Can change which side to start from
            Maze maze = new Maze(inputMazeFile, direction);
            String method = cmd.getOptionValue("method", "righthand");
            Solver solver = SolverFactory.createSolver(method,direction);
            Path path = solver.solve(maze);
            if (cmd.getOptionValue("p") != null) {
                String checkPath = cmd.getOptionValue("p");
                if (path.getCanonicalForm().equals(checkPath) || path.getFactorizedForm().replace(" ","").equals(checkPath.replace(" ",""))) {
                    System.out.println("Correct path");
                } else {
                    System.out.println("incorrect path");
                }
            } else{
                System.out.println("Canonical path to exit: " + path.getCanonicalForm());
                System.out.println("Factorized path to exit: " + path.getFactorizedForm());
            }

        } catch (Exception e){
            System.exit(1);
        }

    }

}

