import services.AggregationService;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class InstructionProcessor {

    private final AggregationService aggregatorService;

    public InstructionProcessor(AggregationService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    public void incomingSettlement() {
        System.out.println("Incoming settlement (Selling)");
        System.out.println("-----------------------------");
        aggregatorService.incomingByDate().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(InstructionProcessor::printEntry);
    }

    public void outgoingSettlement() {
        System.out.println("Outgoing settlement (Buying)");
        System.out.println("----------------------------");
        aggregatorService.outgoingByDate().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(InstructionProcessor::printEntry);
    }

    public void incomingRank() {
        System.out.println("Incoming rank (Selling)");
        System.out.println("-----------------------");
        aggregatorService.incomingRank().stream().forEachOrdered(System.out::println);
    }

    public void outgoingRank() {
        System.out.println("Outgoing rank (Buying)");
        System.out.println("----------------------");
        aggregatorService.outgoingRank().stream().forEachOrdered(System.out::println);
    }

    public static void printEntry(Map.Entry entry) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        printEntry(entry, numberFormat);
    }

    public static void printEntry(Map.Entry entry, NumberFormat numberFormat) {
        System.out.println(entry.getKey() + " : " + numberFormat.format(entry.getValue()));
    }

}
