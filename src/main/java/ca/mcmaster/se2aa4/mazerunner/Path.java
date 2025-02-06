package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Character> steps;

    /**
     * Initializes an empty path.
     */
    public Path() {
        this.steps = new ArrayList<>();
    }

    /**
     * Adds a step to the path.
     *
     * @param step The movement instruction ('F' for forward).
     */
    public void addStep(char step) {
        if (step == 'F') {
            steps.add(step);
        } else {
            throw new IllegalArgumentException("Invalid step. Only 'F' (forward) is supported.");
        }
    }

    /**
     * Returns the movement instructions as a string.
     *
     * @return A string representation of the path.
     */
    @Override
    public String toString() {
        return String.join("", steps.stream().map(String::valueOf).toList());
    }

    /**
     * Retrieves the list of steps.
     *
     * @return The list of movement steps.
     */
    public List<Character> getSteps() {
        return new ArrayList<>(steps);
    }
}
