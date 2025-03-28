import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Direction;

public class PathRotationTest {

    @Test
    public void testTurnSequenceFromUp() {
        Direction current = Direction.UP;
        current = current.turnRight();  // should be RIGHT
        assertEquals(Direction.RIGHT, current);

        current = current.turnRight();  // should be DOWN
        assertEquals(Direction.DOWN, current);

        current = current.turnLeft();   // should be RIGHT
        assertEquals(Direction.RIGHT, current);
    }
}
