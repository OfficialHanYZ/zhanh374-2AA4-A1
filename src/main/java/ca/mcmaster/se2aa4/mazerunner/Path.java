package ca.mcmaster.se2aa4.mazerunner;

import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Path {
    private List<Character> steps = new ArrayList<>();

    /* addStep
     *
     * Description: Adds a single step to the list of steps in the path
     * Parameters: step, the character representing a step, R L F.
     */
    public void addStep(char step) {
        steps.add(step);
    }

    /* getCanonicalForm
     *
     * Description: Returns the canonical representation of the path
     * Output/Return Value: A string
     */
    public String getCanonicalForm() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < steps.size(); i++) {
            sb.append(steps.get(i));

            if (i < steps.size() - 1 && steps.get(i) != steps.get(i + 1)) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    /* getFactorizedForm
     *
     * Description: Returns a factorized representation of the path
     * Output/Return Value: A string
     */
    public String getFactorizedForm() {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i < steps.size(); i++) {
            if (steps.get(i) == steps.get(i - 1)) {
                count++;
            } else {
                appendStep(sb, steps.get(i - 1), count);
                count = 1;
            }
        }
        appendStep(sb, steps.get(steps.size() - 1), count);

        return sb.toString();
    }

    /* appendStep
     *
     * Description: Helper method that appends a step
     * Parameters: sb, the StringBuilder to append to, step, the character representing the step, count, the number of times the step is repeated consecutively
     */
    private void appendStep(StringBuilder sb, char step, int count) {
        if (count > 1) {
            sb.append(count);
        }
        sb.append(step);
        sb.append(' ');
    }
}
