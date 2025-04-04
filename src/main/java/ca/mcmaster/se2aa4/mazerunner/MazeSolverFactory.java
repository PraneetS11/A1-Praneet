package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolverFactory {

    public static MapSolver createSolver(String method, Map map) {
        return switch (method.toLowerCase()) {
            case "rhs" -> new RightHandSolver(map);
            case "tremaux" -> new TremauxSolver(map); // Placeholder
            default -> throw new IllegalArgumentException("Unknown solving method: " + method);
        };
    }
}
