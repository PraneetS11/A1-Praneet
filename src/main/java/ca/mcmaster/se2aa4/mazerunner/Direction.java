package ca.mcmaster.se2aa4.mazerunner;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    /**
     * Returns the direction when turning right (clockwise).
     *
     * @return The new direction after turning right.
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
     * Returns the direction when turning left (counterclockwise).
     *
     * @return The new direction after turning left.
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
     * Returns the direction when making a full turn (180 degrees).
     *
     * @return The new direction after turning around.
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
