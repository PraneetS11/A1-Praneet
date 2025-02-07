package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Character> path = new ArrayList<>();

    /**
     * Initialize an empty Path.
     */
    public Path() {
    }

    /**
     * Initialize path from a Path String.
     *
     * @param pathStr The Path String
     */
    public Path(String pathStr) {
        String expanded = expandFactorizedStringPath(pathStr);
        for (Character c : expanded.toCharArray()) {
            if (c != ' ') {
                if (c != 'F' && c != 'L' && c != 'R') {
                    throw new IllegalArgumentException("Instruction '" + c + "' is invalid. Must be 'F', 'L', or 'R'.");
                }
                addStep(c);
            }
        }
    }

    /**
     * Expands a factorized string path into a full movement path.
     *
     * @param path The factorized string path (e.g., "10F 2R").
     * @return The expanded full string path (e.g., "FFFFFFFFFF RR").
     */
    public static String expandFactorizedStringPath(String path) {
        StringBuilder expanded = new StringBuilder();

        for (int i = 0; i < path.length(); i++) {
            if (!Character.isDigit(path.charAt(i))) {
                expanded.append(path.charAt(i));
            } else {
                int count = 0;
                int digit = 0;
                do {
                    count *= (int) Math.pow(10, digit++);
                    count += Character.getNumericValue(path.charAt(i++));
                } while (Character.isDigit(path.charAt(i)));

                String step = String.valueOf(path.charAt(i)).repeat(count);
                expanded.append(step);
            }
        }

        return expanded.toString();
    }

    /**
     * Get steps of Path.
     *
     * @return Chars of Path
     */
    public List<Character> getPathSteps() {
        return new ArrayList<>(this.path);
    }

    /**
     * Adds a step to the path.
     *
     * @param step The step that needs to be added.
     */
    public void addStep(Character step) {
        path.add(step);
    }

    /**
     * Generates the canonical form of the maze path.
     *
     * @return A string of the canonical form of a path.
     */
    public String getCanonicalForm() {
        if (path.isEmpty()) {
            return "";
        }

        StringBuilder canonicalPath = new StringBuilder();
        char lastStep = path.get(0);
        int repeatCount = 1;

        for (int i = 1; i < path.size(); i++) {
            char currentStep = path.get(i);
            if (currentStep == lastStep) {
                repeatCount++;
            } else {
                if (repeatCount > 1) {
                    canonicalPath.append(repeatCount);
                }
                canonicalPath.append(lastStep).append(' '); // Ensure space for readability
                lastStep = currentStep;
                repeatCount = 1;
            }
        }

        // Append last recorded step
        if (repeatCount > 1) {
            canonicalPath.append(repeatCount);
        }
        canonicalPath.append(lastStep);

        return canonicalPath.toString().trim(); // Trim trailing space
    }

    /**
     * Generates the factorized form of the maze path.
     *
     * @return A string of the factorized form of a path (e.g., "10F 2R").
     */
    public String getFactorizedForm() {
        if (path.isEmpty()) {
            return "";
        }

        StringBuilder factorizedPath = new StringBuilder();
        char lastStep = path.get(0);
        int repeatCount = 1;

        for (int i = 1; i < path.size(); i++) {
            char currentStep = path.get(i);
            if (currentStep == lastStep) {
                repeatCount++;
            } else {
                factorizedPath.append(repeatCount).append(lastStep).append(' '); // Ensure space for readability
                lastStep = currentStep;
                repeatCount = 1;
            }
        }

        // Append last recorded step
        factorizedPath.append(repeatCount).append(lastStep);

        return factorizedPath.toString().trim(); // Trim trailing space
    }
    @Override
    public String toString() {
        return expandFactorizedStringPath(getFactorizedForm());
    }
    
}
