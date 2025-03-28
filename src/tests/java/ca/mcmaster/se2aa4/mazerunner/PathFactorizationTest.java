import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Path;

public class PathFactorizationTest {
    @Test
    public void testFactorizedForm() {
        Path path = new Path("FFFRRR");
        assertEquals("3F 3R", path.getFactorizedForm());
    }
}
