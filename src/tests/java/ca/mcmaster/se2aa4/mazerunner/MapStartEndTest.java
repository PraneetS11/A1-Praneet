import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.Map;
import ca.mcmaster.se2aa4.mazerunner.Location;

public class MapStartEndTest {
    @Test
    public void testStartEndNotNull() throws Exception {
        Map map = new Map("src/tests/resources/easy.maz.txt");
        assertNotNull(map.getStart());
        assertNotNull(map.getEnd());
    }
}
