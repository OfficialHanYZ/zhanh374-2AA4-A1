package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandAlgorithm implements Solver {

    private String direction;

    /* RightHandAlgorithm
     *
     * Description: Initializes the RightHandAlgorithm solver
     * Parameters: direction, the direction from which to start solving the maze
     */
    public RightHandAlgorithm(String direction) {
        this.direction = direction;
    }
    
    /* solve
     *
     * Description: Solves the maze using the right hand rule algorithm
     * Parameters: maze, the Maze object to be solved
     * Output/Return Value: Returns a Path object containing the steps taken to solve the maze
     */
    public Path solve(Maze maze) {
        Position.Direction startDirection = direction.equals("west") ? Position.Direction.EAST : Position.Direction.WEST;
        Position position = new Position((maze.getStart())[0], (maze.getStart())[1], startDirection);
        Path path = new Path();

        while (position.getRow() != (maze.getEnd())[0] || position.getCol() != (maze.getEnd())[1]) {

            if (canTurnRight(maze, position)) {
                position.turnRight();
                path.addStep('R');
                position.moveForward();
                path.addStep('F');
            } 
            else if (canMoveForward(maze, position)) {
                position.moveForward();
                path.addStep('F');
            } 
            else if (canTurnLeft(maze, position)){
                position.turnLeft();
                path.addStep('L');
                position.moveForward();
                path.addStep('F');
            } 
            else {
                position.turnRight();
                path.addStep('R');
                position.turnRight();
                path.addStep('R');
            }
        }
        
        return path;
    }

    /* canMoveForward
     *
     * Description: Checks if the position can move one step forward in the current direction
     * Parameters: maze, the Maze object to check the validity of the move, position, the current Position object to check from
     * Output/Return Value: Returns true if the move forward is valid, otherwise false
     */
    private boolean canMoveForward(Maze maze, Position position) {
        int newRow = position.getRow();
        int newCol = position.getCol();

        switch (position.getDirection()) {
            case NORTH: newCol--; break;
            case EAST: newRow++; break;
            case SOUTH: newCol++; break;
            case WEST: newRow--; break;
        }

        return maze.isValid(newRow, newCol);
    }

    /* canTurnRight
     *
     * Description: Checks if the position can turn right and move forward
     * Parameters: maze, the Maze object to check the validity of the move, position, the current Position object to check from
     * Output/Return Value: Returns true if the turn right and move forward are valid, otherwise false
     */
    private boolean canTurnRight(Maze maze, Position position) {
        Position.Direction newDirection = position.getDirection().turnRight();
        int newRow = position.getRow();
        int newCol = position.getCol();
        
        switch (newDirection) {
            case NORTH: newCol--; break;
            case EAST: newRow++; break;
            case SOUTH: newCol++; break;
            case WEST: newRow--; break;
        }
        return maze.isValid(newRow, newCol);
    }

    /* canTurnLeft
     *
     * Description: Checks if the position can turn left and move forward.
     * Parameters: maze, the Maze object to check the validity of the move, position, the current Position object to check from
     * Output/Return Value: Returns true if the turn right and move forward are valid, otherwise false
     */
    private boolean canTurnLeft(Maze maze, Position position) {
        Position.Direction newDirection = position.getDirection().turnLeft();
        int newRow = position.getRow();
        int newCol = position.getCol();
        
        switch (newDirection) {
            case NORTH: newCol--; break;
            case EAST: newRow++; break;
            case SOUTH: newCol++; break;
            case WEST: newRow--; break;
        }
        return maze.isValid(newRow, newCol);
    }
}
