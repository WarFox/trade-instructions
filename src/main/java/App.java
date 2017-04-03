import models.Instruction;
import models.TransactionType;
import services.AggregationService;
import services.SimpleAggregationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class App {

    public static void main(String[] args) {
        final List<Instruction> instructions = new App().getInstructions();
        final AggregationService aggregatorService = new SimpleAggregationService(instructions);
        final InstructionProcessor instructionProcessor = new InstructionProcessor(aggregatorService);
        instructionProcessor.incomingSettlement();
        System.out.println("============================");
        instructionProcessor.outgoingSettlement();
        System.out.println("============================");
        instructionProcessor.incomingRank();
        System.out.println("============================");
        instructionProcessor.outgoingRank();
    }

    public List<Instruction> getInstructions() {
        final List<Instruction> instructions = new ArrayList<>();
        TransactionType B = TransactionType.B;
        TransactionType S = TransactionType.S;

        instructions.add(getInstruction("foo", B, new BigDecimal(0.50), currency("SGD"), date("01 Jan 2017"), date("02 Jan 2017"), 200, new BigDecimal(100.25)));
        instructions.add(getInstruction("foo", S, new BigDecimal(0.50), currency("SGD"), date("01 Jan 2017"), date("02 Jan 2017"), 100, new BigDecimal(100.25)));
        instructions.add(getInstruction("bar", S, new BigDecimal(0.22), currency("AED"), date("02 Jan 2017"), date("07 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("bar", B, new BigDecimal(0.22), currency("AED"), date("03 Jan 2017"), date("08 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("bar", S, new BigDecimal(1.25), currency("GBP"), date("03 Jan 2017"), date("07 Jan 2017"), 500, new BigDecimal(150.5)));
        instructions.add(getInstruction("grunt", S, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("09 Jan 2017"), 450, new BigDecimal(172.5)));
        instructions.add(getInstruction("bar", S, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("06 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("bar", B, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("07 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("thud", S, new BigDecimal(1.07), currency("EUR"), date("05 Jan 2017"), date("10 Jan 2017"), 450, new BigDecimal(255.75)));
        instructions.add(getInstruction("bar", S, new BigDecimal(0.24), currency("AED"), date("06 Jan 2017"), date("07 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("foo", S, new BigDecimal(0.23), currency("AED"), date("09 Jan 2017"), date("10 Jan 2017"), 450, new BigDecimal(101.5)));
        instructions.add(getInstruction("bar", B, new BigDecimal(0.22), currency("AED"), date("09 Jan 2017"), date("11 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("thud", S, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("12 Jan 2017"), 450, new BigDecimal(256.5)));
        instructions.add(getInstruction("bar", B, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("13 Jan 2017"), 450, new BigDecimal(150.5)));
        instructions.add(getInstruction("foo", S, new BigDecimal(0.22), currency("AED"), date("05 Jan 2017"), date("13 Jan 2017"), 450, new BigDecimal(102.75)));

        return instructions;
    }

    private LocalDate date(String date) {
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return LocalDate.parse(date, dtf);
    }

    private Currency currency(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    private Instruction getInstruction(String entity, TransactionType transactionType, BigDecimal agreedFx, Currency currency, LocalDate instructionDate, LocalDate settlementDate, long units, BigDecimal pricePerUnit) {
        return new Instruction.Builder()
                .entity(entity)
                .transactionType(transactionType)
                .agreedFx(agreedFx)
                .currency(currency)
                .instructionDate(instructionDate)
                .settlementDate(settlementDate)
                .units(units)
                .pricePerUnit(pricePerUnit)
                .build();
    }
}
