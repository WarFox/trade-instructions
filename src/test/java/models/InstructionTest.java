package models;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

public class InstructionTest {

    @Test
    public void testDefaultEventBuilder() throws Exception {
        final Instruction instruction = new Instruction.Builder().build();
        assertThat(instruction).isNotNull();
    }

    @Test
    public void testBuildEventWithEntity() throws Exception {
        final Instruction instruction = new Instruction.Builder().entity("foo").build();
        assertThat(instruction.getEntity()).isEqualTo("foo");
    }

    @Test
    public void testBuildEventWithTransactionType() throws Exception {
        final Instruction instruction = new Instruction.Builder().transactionType(TransactionType.B).build();
        assertThat(instruction.getTransactionType()).isEqualTo(TransactionType.B);
    }

    @Test
    public void testBuildEventWithAgreedFx() throws Exception {
        final Instruction instruction = new Instruction.Builder().agreedFx(BigDecimal.valueOf(10.20)).build();
        assertThat(instruction.getAgreedFx()).isEqualTo(BigDecimal.valueOf(10.20));
    }

    @Test
    public void testBuildEventWithCurrencyCode() throws Exception {
        final Instruction instruction = new Instruction.Builder().currency(Currency.getInstance("GBP")).build();
        assertThat(instruction.getCurrency()).isEqualTo(Currency.getInstance("GBP"));
    }

    @Test
    public void testBuildEventWithInstructionDate() throws Exception {
        final Instruction instruction = new Instruction.Builder().instructionDate(LocalDate.of(2017, 1, 1)).build();
        assertThat(instruction.getInstructionDate()).isEqualTo(LocalDate.of(2017, 1, 1));
    }

    @Test
    public void testBuildEventWithSettlementDate() throws Exception {
        final Instruction instruction = new Instruction.Builder().settlementDate(LocalDate.of(2017, 1, 2)).build();
        assertThat(instruction.getSettlementDate()).isEqualTo(LocalDate.of(2017, 1, 2));
    }

    @Test
    public void testBuildEventWithUnits() throws Exception {
        final Instruction instruction = new Instruction.Builder().units(10).build();
        assertThat(instruction.getUnits()).isEqualTo(10);
    }

    @Test
    public void testPricePerUnit() throws Exception {
        final Instruction instruction = new Instruction.Builder().pricePerUnit(BigDecimal.valueOf(10.10)).build();
        assertThat(instruction.getPricePerUnit()).isEqualTo(BigDecimal.valueOf(10.10));
    }

    @Test
    public void testSellIsIncoming() throws Exception {
        Instruction instruction = new Instruction.Builder().transactionType(TransactionType.S).build();
        assertThat(instruction.isIncoming()).isTrue();
    }

    @Test
    public void testBuyIsNotIncoming() throws Exception {
        Instruction instruction = new Instruction.Builder().transactionType(TransactionType.B).build();
        assertThat(instruction.isIncoming()).isFalse();
    }

    @Test
    public void testBuyIsOutgoing() throws Exception {
        Instruction instruction = new Instruction.Builder().transactionType(TransactionType.B).build();
        assertThat(instruction.isOutgoing()).isTrue();
    }

    @Test
    public void testGetAdjustSettlementDateWeekday() throws Exception {
        LocalDate weekDay = LocalDate.of(2017, 1, 4);
        Instruction instruction = new Instruction.Builder().currency(Currency.getInstance("USD")).settlementDate(weekDay).build();
        assertThat(instruction.getAdjustedSettlementDate()).isEqualTo(weekDay);
    }

    @Test
    public void testGetAdjustSettlementDateWeekend() throws Exception {
        LocalDate weekDay = LocalDate.of(2017, 1, 7);
        Instruction instruction = new Instruction.Builder().currency(Currency.getInstance("USD")).settlementDate(weekDay).build();
        assertThat(instruction.getAdjustedSettlementDate()).isEqualTo(LocalDate.of(2017, 1, 9));
    }

    @Test
    public void testGetAdjustSettlementDateWeekendAED() throws Exception {
        LocalDate weekDay = LocalDate.of(2017, 1, 7);
        Instruction instruction = new Instruction.Builder().currency(Currency.getInstance("AED")).settlementDate(weekDay).build();
        assertThat(instruction.getAdjustedSettlementDate()).isEqualTo(LocalDate.of(2017, 1, 8));
    }

}
