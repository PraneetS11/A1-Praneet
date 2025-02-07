package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    /**
     * Get the direction to the right of the current one (clockwise).
     *
     * @return The direction to the right.
     */
    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    /**
     * Get the direction to the left of the current one (counter-clockwise).
     *
     * @return The direction to the left.
     */
    public Direction turnLeft() {
        return switch (this) {
            case UP -> LEFT;
            case LEFT -> DOWN;
            case DOWN -> RIGHT;
            case RIGHT -> UP;
        };
    }

    /**
     * Get the opposite direction (180-degree turn).
     *
     * @return The opposite direction.
     */
    public Direction turnAround() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
