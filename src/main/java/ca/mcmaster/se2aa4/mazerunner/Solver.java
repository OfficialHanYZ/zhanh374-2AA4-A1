package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Solver {
    private Maze maze;
    private int[] position;
    private char direction;
    private List<Character> path;

    public Solver(Maze maze) {
        this.maze = maze;
        this.position = maze.getStart();
        this.direction = 'E';
        this.path = new ArrayList<>();
    }

    private final char[] directions = {'N', 'E', 'S', 'W'};

    public List<Character> explore() {
        while (position[0] != (maze.getEnd())[0] || position[1] != (maze.getEnd())[1]) {
            //turnRight();
            if (canMoveForward()) {
                moveForward();
                path.add('F');
            } else {
                turnLeft();
            }
        }
        return path;
    }

    private boolean canMoveForward() {
        int x = position[1], y = position[0];
        switch (direction) {
            case 'N': return !maze.isWall(x, y - 1);
            case 'E': return !maze.isWall(x + 1, y);
            case 'S': return !maze.isWall(x, y + 1);
            case 'W': return !maze.isWall(x - 1, y);
            default: return false;
        }
    }

    private void moveForward() {
        int x = position[1], y = position[0];
        switch (direction) {
            case 'N': position[0] = y - 1; break;
            case 'E': position[1] = x + 1; break;
            case 'S': position[0] = y + 1; break;
            case 'W': position[1] = x - 1; break;
        }
    }

    private void turnRight() {
        int index = new String(directions).indexOf(direction);
        direction = directions[(index + 1) % 4];
        path.add('R');
    }

    private void turnLeft() {
        int index = new String(directions).indexOf(direction);
        direction = directions[(index + 3) % 4];
        path.add('L');
    }
}