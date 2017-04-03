package models;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.Before;

public class TransactionTypeTest {

    @Test
    public void testTransactionTypeValueOfB() throws Exception {
        final TransactionType transactionType = TransactionType.B;
        assertThat(transactionType.toString()).isEqualTo("B");
    }

    @Test
    public void testTransactionTypeValueOfS() throws Exception {
        final TransactionType transactionType = TransactionType.S;
        assertThat(transactionType.toString()).isEqualTo("S");
    }

    @Test
    public void testValueOfB() throws Exception {
        assertThat(TransactionType.valueOf("B")).isEqualTo(TransactionType.B);
    }

    @Test
    public void testValueOfS() throws Exception {
        assertThat(TransactionType.valueOf("S")).isEqualTo(TransactionType.S);
    }

    @Test
    public void testValues() throws Exception {
        assertThat(TransactionType.values()).containsOnly(TransactionType.B, TransactionType.S);
    }

}
