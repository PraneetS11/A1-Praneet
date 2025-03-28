import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathCanonicalFormTest {
    @Test
    public void testCanonicalForm() {
        Path path = new Path("FFFLLF");
        assertEquals("3F 2L F", path.getCanonicalForm());
    }
}
