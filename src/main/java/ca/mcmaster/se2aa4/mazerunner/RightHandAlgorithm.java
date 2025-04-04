package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgorithm extends AbstractSolver {
    public RightHandAlgorithm(String direction) {
        super(direction);
    }

    @Override
    protected void move(Position position, Path path, Maze maze) {
        if (canTurnRight(maze, position)) {
            position.turnRight();
            path.addStep('R');
            position.moveForward();
            path.addStep('F');
        } else if (canMoveForward(maze, position)) {
            position.moveForward();
            path.addStep('F');
        } else if (canTurnLeft(maze, position)) {
            position.turnLeft();
            path.addStep('L');
            position.moveForward();
            path.addStep('F');
        } else {
            position.turnRight();
            path.addStep('R');
            position.turnRight();
            path.addStep('R');
        }
    }
}