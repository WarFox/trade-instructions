import org.junit.Test;
import services.AggregationService;

import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class InstructionProcessorTest {

    @Test
    public void testClassHasIncomingSettlement() throws Exception {
        AggregationService aggregationService = mock(AggregationService.class);
        InstructionProcessor instructionProcessor = new InstructionProcessor(aggregationService);
        instructionProcessor.incomingSettlement();
    }

    @Test
    public void testClassHasOutgoingSettlement() throws Exception {
        AggregationService aggregationService = mock(AggregationService.class);
        InstructionProcessor instructionProcessor = new InstructionProcessor(aggregationService);
        instructionProcessor.outgoingSettlement();
    }

    @Test
    public void testClassHasIncomingRank() throws Exception {
        AggregationService aggregationService = mock(AggregationService.class);
        InstructionProcessor instructionProcessor = new InstructionProcessor(aggregationService);
        instructionProcessor.incomingRank();
    }

    @Test
    public void testClassHasOutgoingRank() throws Exception {
        AggregationService aggregationService = mock(AggregationService.class);
        InstructionProcessor instructionProcessor = new InstructionProcessor(aggregationService);
        instructionProcessor.outgoingRank();
    }

    @Test
    public void testPrintEntry() throws Exception {
        Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("key", 20);
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        InstructionProcessor.printEntry(entry);
        verify(out).println("key : $20.00");
    }

}
