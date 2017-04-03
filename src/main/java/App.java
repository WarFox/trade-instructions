import models.Instruction;
import models.TransactionType;
import services.SimpleAggregationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class App {

    public static void main(String[] args) {
        final List<Instruction> instructions = new App().getInstructions();
        final SimpleAggregationService aggregatorService = new SimpleAggregationService(instructions);
        final InstructionProcessor instructionProcessor = new InstructionProcessor(aggregatorService);
        instructionProcessor.incomingSettlement();
        instructionProcessor.outgoingSettlement();
        instructionProcessor.incomingRank();
        instructionProcessor.outgoingRank();
    }

    public List<Instruction> getInstructions() {
        final List<Instruction> instructions = new ArrayList<>();
        LocalDate janFirst = LocalDate.of(2017, 1, 1);
        LocalDate janSecond = LocalDate.of(2017, 1, 2);
        TransactionType B = TransactionType.B;
        TransactionType S = TransactionType.S;
        instructions.add(getInstruction("foo", B, new BigDecimal(0.50), currency("SGD"), janFirst, janFirst, 200, new BigDecimal(100.25)));
        instructions.add(getInstruction("bar", S, new BigDecimal(0.22), currency("AED"), janFirst, janFirst, 450, new BigDecimal(150.25)));
        instructions.add(getInstruction("foo", B, BigDecimal.ONE, currency("AED"), janFirst, janFirst, 2, BigDecimal.ONE));
        instructions.add(getInstruction("bar", B, BigDecimal.ONE, currency("AED"), janFirst, janFirst, 2, BigDecimal.ONE));
        instructions.add(getInstruction("thud", S, BigDecimal.ONE, currency("AED"), janFirst, janSecond, 2, BigDecimal.ONE));
        instructions.add(getInstruction("grunt", B, BigDecimal.ONE, currency("AED"), janFirst, janFirst, 2, BigDecimal.ONE));
        instructions.add(getInstruction("bar", S, BigDecimal.ONE, currency("AED"), janFirst, janSecond, 2, BigDecimal.ONE));
        instructions.add(getInstruction("foo", B, BigDecimal.ONE, currency("AED"), janFirst, janFirst, 2, BigDecimal.ONE));
        instructions.add(getInstruction("foo", S, BigDecimal.ONE, currency("AED"), janFirst, janSecond, 2, BigDecimal.ONE));
        instructions.add(getInstruction("thud", S, BigDecimal.ONE, currency("AED"), janFirst, janFirst, 2, BigDecimal.ONE));
        instructions.add(getInstruction("thud", B, BigDecimal.ONE, currency("AED"), janFirst, janSecond, 2, BigDecimal.ONE));
        return instructions;
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
