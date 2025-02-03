package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {
    private char[][] maze;
    private int[] start = new int[2];
    private int[] end = new int[2];
    private int width;
    private int height;
    private String direction;

/* Maze 
 *
 * Description: Initializes a maze 
 * Parameters: filename, the file for the maze. direction, which side to start from
 */

    public Maze(String filename, String direction) {
        this.direction = direction;
        loadMaze(filename);
    }

/* loadMaze 
 *
 * Description: Puts every node of the maze into a 2d array and gets the start and end coordinates.
 * Parameters: filename, the file for the maze
 */
    private void loadMaze(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                } else {
                    lines.add(" ");
                }
            }

            reader.close();

            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Maze file is empty or improperly formatted.");
            }

            width = 0;
            for (String l : lines) {
                width = Math.max(width, l.length());
            }

            height = lines.size();
            maze = new char[width][height];

            for (int y = 0; y < height; y++) {
                String row = lines.get(y);
                for (int x = 0; x < width; x++) {
                    maze[x][y] = (x < row.length()) ? row.charAt(x) : ' ';
                }
            }

            for (int y = 0; y < height; y++) {
                if (maze[0][y] == ' ') {
                    start[0] = 0;
                    start[1] = y;
                }
                if (maze[width - 1][y] == ' ') {
                    end[0] = width - 1;
                    end[1] = y;
                }
            }

            if (direction.equals("east")){
                int[] temp = start;
                start = end;
                end = temp;
            }

        } catch (IOException e) {
            System.err.println("Error reading maze file: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

/* getStart 
 *
 * Description: Returns the starting coords
 * Output/Return Value: The starting coordinates
 */
    public int[] getStart() {
        return start;
    }

/* getEnd 
 *
 * Description: Returns the ending coords
 * Output/Return Value: The ending coordinates
 */
    public int[] getEnd() {
        return end;
    }

/* isValid 
 *
 * Description: Checks if the coordinate that the algorithm intends to go to is valid
 * Parameters: x and y coordinates
 * Output/Return Value: True or false depending on if the space is open or a wall
 */
    public boolean isValid(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height && maze[x][y] == ' ';
    }
}