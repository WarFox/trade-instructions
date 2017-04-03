package services;

import models.Instruction;
import models.Settlement;
import models.TransactionType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleAggregationServiceTest {

    @Test
    public void testOutgoingByDate() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        Map<LocalDate, BigDecimal> outgoingByDate = aggregatorService.outgoingByDate();
        assertThat(outgoingByDate.get(LocalDate.of(2017, 1, 3))).isEqualByComparingTo(new BigDecimal("8.0"));
    }

    @Test
    public void testIncomingByDate() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        Map<LocalDate, BigDecimal> incomingByDate = aggregatorService.incomingByDate();
        assertThat(incomingByDate.get(LocalDate.of(2017, 1, 3))).isEqualByComparingTo(new BigDecimal("8.0"));
    }

    @Test
    public void testSettlements() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        List<Settlement> settlements = aggregatorService.getSettlements();
        assertThat(settlements).hasSize(6);
    }

    @Test
    public void testIncomingRank() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        List<String> entitiesIncomingRank = aggregatorService.incomingRank();
        assertThat(entitiesIncomingRank).hasSize(3);
        assertThat(entitiesIncomingRank).containsExactly("thud", "bar", "foo");
    }

    @Test
    public void testOutgoingRank() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        List<String> entitiesOutgoingRank = aggregatorService.outgoingRank();
        assertThat(entitiesOutgoingRank).hasSize(3);
        assertThat(entitiesOutgoingRank).containsExactly("foo", "grunt", "bar");
    }

    @Test
    public void testGetSettlementDoesLazyCalculation() throws Exception {
        SimpleAggregationService aggregatorService = new SimpleAggregationService(getWeekdayInstructions());
        List<Settlement> firstSettlements = aggregatorService.getSettlements();
        List<Settlement> secondSettlements = aggregatorService.getSettlements();
        assertThat(firstSettlements == secondSettlements).isTrue();
    }

    private List<Instruction> getWeekdayInstructions() throws Exception {
        ArrayList<Instruction> instructions = new ArrayList<>();
        instructions.add(getInstruction("foo", TransactionType.B, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("bar", TransactionType.B, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("foo", TransactionType.B, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("grunt", TransactionType.B, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));

        instructions.add(getInstruction("bar", TransactionType.S, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("thud", TransactionType.S, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("foo", TransactionType.S, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        instructions.add(getInstruction("thud", TransactionType.S, BigDecimal.ONE, Currency.getInstance("AED"), LocalDate.of(2017, 1, 2), LocalDate.of(2017, 1, 3), 2, BigDecimal.ONE));
        return instructions;
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
                .pricePerUnit(pricePerUnit).build();
    }

}
