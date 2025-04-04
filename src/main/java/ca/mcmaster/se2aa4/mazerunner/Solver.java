package ca.mcmaster.se2aa4.mazerunner;

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