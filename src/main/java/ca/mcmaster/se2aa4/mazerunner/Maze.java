package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Maze {
    private char[][] maze;
    private int[] start = new int[2];
    private int[] end = new int[2];
    private int width; 
    
    public Maze(String filename) {
        loadMaze(filename);
    }

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
            int height = lines.size();
            maze = new char[height][width];

            for (int y = 0; y < height; y++) {
                String row = lines.get(y);

                for (int x = 0; x < width; x++) {
                    if (x < row.length()) {
                        maze[y][x] = row.charAt(x);
                    } else {
                        maze[y][x] = ' '; 
                    }
                }
            }

            for (int x = 0; x < maze.length; x++){
                if (maze[x][0] == ' '){
                    start[0] = x;
                    start[1] = 0;
                }
            }

            for (int y = 0; y < maze.length; y++){
                if (maze[y][width-1] == ' '){
                    end[0] = y;
                    end[1] = width-1;
                }
            }

            System.out.println(start[0]);
            System.out.println(end[0]);

        } catch (IOException e) {
            System.err.println("Error reading maze file: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printMaze() {
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                System.out.print(maze[y][x]); 
            }
            System.out.println();
        }
    }

    public char getCell(int x, int y) {
        return maze[y][x];
    }

    public int[] getStart() {
        return start;
    }

    public int[] getEnd() {
        return end;
    }

    public char[][] getMaze() {
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return maze.length;
    }

    public boolean isWall(int x, int y) {
        return maze[y][x] == '#';
    }
}