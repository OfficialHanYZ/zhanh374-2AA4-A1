package ca.mcmaster.se2aa4.mazerunner;

public class SolverFactory {

    /**
     * createSolver
     * 
     * Description: Implementation of the factory method design pattern, allows the type of object creation to be determined at runtime
     * Parameters: method is the selected algorithm, direction is the side that the bot starts at
     * Output/Return Value: returns a Solver object with the chosen algorithm
     */
    public static Solver createSolver(String method, String direction) {
        return switch (method.toLowerCase()) {
            case "righthand" -> new RightHandAlgorithm(direction);
            default -> throw new IllegalArgumentException("Unknown solving method: " + method);
        };
    }
}