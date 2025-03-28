import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathExpansionTest {
    @Test
    public void testExpandFactorizedPath() {
        String input = "3F 2R";
        String expected = "FFF RR";
        assertEquals(expected, Path.expandFactorizedStringPath(input));
    }
}
