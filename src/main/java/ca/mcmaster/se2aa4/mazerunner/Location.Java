package ca.mcmaster.se2aa4.mazerunner;

public record Location(int x, int y) {
    /**
     * Adds another Location to this Location and return the sum of both.
     *
     * @param other Other Location
     * @return Sum of both Locations
     */
    public Location add(Location other) {
        return new Location(this.x + other.x, this.y + other.y);
    }

    /**
     * Return the new Location after moving in provided direction.
     *
     * @param direction Direction in which to move
     * @return The new Location
     */
    public Location move(Direction direction) {
        switch (direction) {
            case UP -> {
                return this.add(new Location(0, -1));
            }
            case DOWN -> {
                return this.add(new Location(0, 1));
            }
            case LEFT -> {
                return this.add(new Location(-1, 0));
            }
            case RIGHT -> {
                return this.add(new Location(1, 0));
            }
        }
        throw new IllegalStateException("Unexpected value: " + this);
    }

    @Override
    public String toString() {
        return "Location(" + this.x + "," + this.y + ")";
    }
}