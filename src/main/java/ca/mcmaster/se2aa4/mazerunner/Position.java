package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Position {
    private int row, col;
    private Direction direction;

    /* Position 
     *
     * Description: Initializes a position in the maze with a specified row, column, and direction
     * Parameters: row, col, direction, the direction the position is facing
     */
    public Position(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    /* getRow 
     *
     * Description: Retrieves the current row of the position in the maze
     * Output/Return Value: Returns the row of the position
     */
    public int getRow() { 
        return row; 
    }

    /* getCol 
     *
     * Description: Retrieves the current column of the position in the maze
     * Output/Return Value: Returns the column of the position
     */
    public int getCol() { 
        return col; 
    }

    /* getDirection 
     *
     * Description: Retrieves the current direction of the current position
     * Output/Return Value: Returns the current direction of the position
     */
    public Direction getDirection() { 
        return direction; 
    }

    /* moveForward 
     *
     * Description: Moves the position one step forward in the direction it's facing
     */
    public void moveForward() {
        switch (direction) {
            case NORTH -> col--;
            case EAST -> row++;
            case SOUTH -> col++;
            case WEST -> row--;
        }
    }

    /* turnRight 
     *
     * Description: Turns the position right
     */
    public void turnRight() {
        direction = direction.turnRight();
    }

    /* turnLeft 
     *
     * Description: Turns the position left
     */
    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;

        /* turnRight 
         *
         * Description: Turns the current direction right
         * Output/Return Value: Returns the new direction after turning right
         */
        public Direction turnRight() {
            switch (this) {
                case NORTH: return EAST;
                case EAST: return SOUTH;
                case SOUTH: return WEST;
                case WEST: return NORTH;
                default: throw new IllegalArgumentException("Unknown direction");
            }
        }

        /* turnLeft 
         *
         * Description: Turns the current direction left
         * Output/Return Value: Returns the new direction after turning left
         */
        public Direction turnLeft() {
            switch (this) {
                case NORTH: return WEST;
                case WEST: return SOUTH;
                case SOUTH: return EAST;
                case EAST: return NORTH;
                default: throw new IllegalArgumentException("Unknown direction");
            }
        }
    }
}
