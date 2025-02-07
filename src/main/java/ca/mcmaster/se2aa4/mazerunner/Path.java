package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param step The movement instruction ('F' for forward, 'L' for left, 'R' for right).
     */
    public void addStep(char step) {
        if (step == 'F' || step == 'L' || step == 'R') {
            steps.add(step);
        } else {
            throw new IllegalArgumentException("Invalid step. Only 'F' (forward), 'L' (left), and 'R' (right) are supported.");
        }
    }

    /**
     * Returns the movement instructions as a string.
     *
     * @return A string representation of the path.
     */
    @Override
    public String toString() {
        return steps.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Retrieves the list of steps.
     *
     * @return The list of movement steps.
     */
    public List<Character> getSteps() {
        return new ArrayList<>(steps);
    }

    /**
     * Returns a canonical form of the path by compressing repetitive steps.
     *
     * @return A compressed string representation of the path.
     */
    public String getCanonicalForm() {
        if (steps.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char prev = steps.get(0);
        int count = 1;

        for (int i = 1; i < steps.size(); i++) {
            char current = steps.get(i);
            if (current == prev) {
                count++;
            } else {
                result.append(prev);
                if (count > 1) {
                    result.append(count);
                }
                prev = current;
                count = 1;
            }
        }

        result.append(prev);
        if (count > 1) {
            result.append(count);
        }

        return result.toString();
    }
}
