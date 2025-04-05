package ca.mcmaster.se2aa4.mazerunner;

public record Location(int x, int y) {

    public Location add(Location other) {
        return new Location(this.x + other.x, this.y + other.y);
    }

    public Location move(Direction direction) {
        return switch (direction) {
            case UP -> this.add(new Location(0, -1));
            case DOWN -> this.add(new Location(0, 1));
            case LEFT -> this.add(new Location(-1, 0));
            case RIGHT -> this.add(new Location(1, 0));
        };
    }

    @Override
    public String toString() {
        return "Location(" + this.x + "," + this.y + ")";
    }
}