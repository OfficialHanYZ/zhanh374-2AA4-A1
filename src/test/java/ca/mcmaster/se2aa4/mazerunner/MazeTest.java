package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {
    private Maze maze;
    private Solver solver;
    private Path path;
    private Position position;

    @BeforeEach
    void setUp() {

        String MazeFile = "./examples/straight.maz.txt";

        try {
            String direction = "west";
            maze = new Maze(MazeFile,direction);
            solver = new RightHandAlgorithm(direction);
            path = solver.solve(maze);
            Position.Direction botDirection = direction.equals("west") ? Position.Direction.EAST : Position.Direction.WEST;
            position = new Position((maze.getStart())[0], (maze.getStart())[1], botDirection); 

        } catch (Exception e) {
            fail("Testing setup failed: " + e.getMessage());
        }
        
    }

    
    @Test
    public void testCanonical() {
        assertEquals(path.getCanonicalForm(),"FFFF");
    }

    @Test 
    public void testFactorized() { 
        assertEquals(path.getFactorizedForm(),"4F ");
    }

    @Test 
    public void testGetRow() { 
        assertEquals(position.getRow(),0);
    }

    @Test 
    public void testGetColumn() { 
        assertEquals(position.getCol(),2);
    }

    @Test 
    public void testGetDirection() { 
        assertEquals(position.getDirection().toString(),"EAST");
    }

    @Test 
    public void testIsValid() { 
        assertEquals(maze.isValid(0,3),false);
    }

    @Test 
    public void testGetEnd() { 
        int[] expected = {4,2};
        assertArrayEquals(maze.getEnd(),expected);
    }

    @Test 
    public void testGetStart() { 
        int[] expected = {0,2};
        assertArrayEquals(maze.getStart(),expected);
    }

    @Test 
    public void testTurnRight() { 
        assertEquals(position.getDirection().turnRight().toString(),"SOUTH");
    }

    @Test 
    public void testTurnLeft() { 
        assertEquals(position.getDirection().turnLeft().toString(),"NORTH");
    }

}