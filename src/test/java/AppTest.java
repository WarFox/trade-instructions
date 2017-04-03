import models.Instruction;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testAppHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should get instructions", classUnderTest.getInstructions());
    }

    @Test
    public void testInstructionsShouldNotBeEmpty() throws Exception {
        List<Instruction> instructions = new App().getInstructions();
        assertFalse(instructions.isEmpty());
    }

}
