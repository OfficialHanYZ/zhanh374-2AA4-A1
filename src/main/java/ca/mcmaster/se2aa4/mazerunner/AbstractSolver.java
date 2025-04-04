package ca.mcmaster.se2aa4.mazerunner;


/* AbstractSolver
 *
 * Description: Implementation of the template method, since each algorithm will all have these common steps, subclasses are purely for how to move.
 * Most of the logic and methods were originally part of RightHandAlgorithm and have been put here instead.
 * Output/Return Value: returns the path that the bot needs to take to reach the exit
 */
public abstract class AbstractSolver implements Solver {
    protected String direction;
    protected Position.Direction startDirection;

    public AbstractSolver(String direction) {
        this.direction = direction;
        this.startDirection = direction.equals("west") ? Position.Direction.EAST : Position.Direction.WEST;
    }

    // Currently the only steps are to initialize, and check if the bot has reached the end, otherwise keep moving per the algo
    // More steps can be added in the future if needed
    public Path solve(Maze maze) {
        Position position = new Position(maze.getStart()[0], maze.getStart()[1], startDirection);
        Path path = new Path();

        while (!hasReachedExit(maze, position)) {
            move(position, path, maze);
        }
        return path;
    }

    protected abstract void move(Position position, Path path, Maze maze);

    protected boolean hasReachedExit(Maze maze, Position position) {
        return position.getRow() == maze.getEnd()[0] && position.getCol() == maze.getEnd()[1];
    }

    // The following act as hooks with default, and can be overwritten if a future algorithm needs that to be the case
    protected boolean canMoveForward(Maze maze, Position position) {
        Position.Direction newDirection = position.getDirection();
        return maze.isValid(nextRow(position, newDirection), nextCol(position, newDirection));
    }
    
    protected boolean canTurnRight(Maze maze, Position position) {
        Position.Direction newDirection = position.getDirection().turnRight();
        return maze.isValid(nextRow(position, newDirection), nextCol(position, newDirection));
    }
    
    protected boolean canTurnLeft(Maze maze, Position position) {
        Position.Direction newDirection = position.getDirection().turnLeft();
        return maze.isValid(nextRow(position, newDirection), nextCol(position, newDirection));
    }
    
    protected int nextRow(Position position, Position.Direction dir) {
        return switch (dir) {
            case EAST -> position.getRow() + 1;
            case WEST -> position.getRow() - 1;
            default -> position.getRow();
        };
    }
    
    protected int nextCol(Position position, Position.Direction dir) {
        return switch (dir) {
            case NORTH -> position.getCol() - 1;
            case SOUTH -> position.getCol() + 1;
            default -> position.getCol();
        };
    }
    
}