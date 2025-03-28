import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.cli.*;

public class CommandLineInputFlagTest {

    @Test
    public void testInputFlagParsedCorrectly() throws ParseException {
        String[] args = { "-i", "maze.txt", "-p", "4F R 2F" };
        Options options = new Options();
        options.addOption("i", true, "Input file");
        options.addOption("p", true, "Proposed path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        assertEquals("maze.txt", cmd.getOptionValue("i"));
        assertEquals("4F R 2F", cmd.getOptionValue("p"));
    }
}
