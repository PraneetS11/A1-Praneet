package ca.mcmaster.se2aa4.mazerunner;

public record Location(int x, int y) {

    /**
     * Creates a new Location by moving in a specified direction.
     *
     * @param direction The direction to move in
     * @return A new Location after movement
     */
    public Location move(Direction direction) {
        return switch (direction) {
            case UP -> new Location(x, y - 1);
            case DOWN -> new Location(x, y + 1);
            case LEFT -> new Location(x - 1, y);
            case RIGHT -> new Location(x + 1, y);
        };
    }
}
