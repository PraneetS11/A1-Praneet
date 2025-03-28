import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Map;
import ca.mcmaster.se2aa4.mazerunner.Location;

public class MapWallDetectionTest {
    @Test
    public void testWallCheck() throws Exception {
        Map map = new Map("src/tests/resources/easy.maz.txt");
        assertTrue(map.isWall(new Location(0, 0)));
    }
}
