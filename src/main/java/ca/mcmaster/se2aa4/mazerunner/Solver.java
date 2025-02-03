package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Solver {
    /**
     * solve
     * 
     * Description: Solves maze and returns the path
     * Parameters: maze to solve
     * Output/Return Value: Path that solves maze
     */
    Path solve(Maze maze);
}