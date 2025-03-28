import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathInvalidInputTest {
    @Test
    public void testInvalidPathThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Path("2F 1X");
        });
    }
}
