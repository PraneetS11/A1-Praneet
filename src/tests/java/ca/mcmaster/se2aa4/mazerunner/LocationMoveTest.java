import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Location;
import ca.mcmaster.se2aa4.mazerunner.Direction;

public class LocationMoveTest {
    @Test
    public void testMoveDirectionUp() {
        Location loc = new Location(2, 2);
        assertEquals(new Location(2, 1), loc.move(Direction.UP));
    }
}
