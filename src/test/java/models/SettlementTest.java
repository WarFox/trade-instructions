package models;


import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class SettlementTest {

    @Test
    public void testCreate() throws Exception {
        new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
    }

    @Test
    public void testGetDate() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
        assertThat(settlement.getDate()).isEqualTo(LocalDate.now());
    }

    @Test
    public void testGetEntity() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
        assertThat(settlement.getEntity()).isEqualTo("entity");
    }

    @Test
    public void testGetAmount() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
        assertThat(settlement.getAmount()).isEqualTo(BigDecimal.ONE);
    }

    @Test
    public void testTransactionType() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
        assertThat(settlement.getTransactionType()).isEqualTo(TransactionType.B);
    }

    @Test
    public void testBuyingIsOutgoing() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.B, BigDecimal.ONE);
        assertThat(settlement.isOutgoing()).isTrue();
    }

    @Test
    public void testSellingIsIncoming() throws Exception {
        Settlement settlement = new Settlement(LocalDate.now(), "entity", TransactionType.S, BigDecimal.ONE);
        assertThat(settlement.isIncoming()).isTrue();
    }

}
