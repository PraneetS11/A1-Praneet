import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Direction;

public class DirectionRotationTest {

    @Test
    public void testTurnLeftFromUp() {
        assertEquals(Direction.LEFT, Direction.UP.turnLeft());
    }

    @Test
    public void testTurnRightFromUp() {
        assertEquals(Direction.RIGHT, Direction.UP.turnRight());
    }

    @Test
    public void testTurnAroundFromLeft() {
        assertEquals(Direction.RIGHT, Direction.LEFT.turnAround());
    }
}
