package models;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class AggregationKeyTest {


    @Test
    public void testCanCreateAggregationKey() throws Exception {
        new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
    }

    @Test
    public void testGetDate() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.getDate()).isEqualTo(LocalDate.MAX);
    }

    @Test
    public void testGetEntity() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.getEntity()).isEqualTo("entity");
    }

    @Test
    public void testGetTransactionType() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.getTransactionType()).isEqualTo(TransactionType.B);
    }

    @Test
    public void testHashCodeSameValues() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.hashCode()).isEqualTo(aggregationKey2.hashCode());
    }

    @Test
    public void testHashCodeDifferentValues() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MIN, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.hashCode()).isNotEqualTo(aggregationKey2.hashCode());
    }

    @Test
    public void testHashCodeNullValues() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, null, null);
        AggregationKey aggregationKey2 = new AggregationKey(null, null, null);
        assertThat(aggregationKey.hashCode()).isEqualTo(aggregationKey2.hashCode());
    }

    @Test
    public void testEqualsSameObject() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.equals(aggregationKey)).isTrue();
    }

    @Test
    public void testEqualsNullValues() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, null, null);
        AggregationKey aggregationKey2 = new AggregationKey(null, null, null);
        assertThat(aggregationKey.equals(aggregationKey2)).isTrue();
        assertThat(aggregationKey2.equals(aggregationKey)).isTrue();
    }

    @Test
    public void testEqualsNullValuesInOneObject() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(null, null, null);
        assertThat(aggregationKey.equals(aggregationKey2)).isFalse();
        assertThat(aggregationKey2.equals(aggregationKey)).isFalse();
    }

    @Test
    public void testEqualsSameValues() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.equals(aggregationKey2)).isTrue();
    }

    @Test
    public void testEqualsToNullIsFalse() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        assertThat(aggregationKey.equals(null)).isFalse();
    }

    @Test
    public void testDateNotEqualsShouldReturnFalse() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(LocalDate.MAX, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(LocalDate.MIN, "entity", TransactionType.B);
        assertThat(aggregationKey.equals(aggregationKey2)).isFalse();
    }

    @Test
    public void testEntityNotEqualsShouldReturnFalse() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(null, "entity2", TransactionType.B);
        assertThat(aggregationKey.equals(aggregationKey2)).isFalse();
        assertThat(aggregationKey2.equals(aggregationKey)).isFalse();
    }

    @Test
    public void testEntityNullEqualsShouldReturnFalse() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, "entity", TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(null, null, TransactionType.B);
        assertThat(aggregationKey.equals(aggregationKey2)).isFalse();
        assertThat(aggregationKey2.equals(aggregationKey)).isFalse();
    }

    @Test
    public void testTransactionTypeNotEqualsShouldReturnFalse() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, null, TransactionType.B);
        AggregationKey aggregationKey2 = new AggregationKey(null, null, TransactionType.S);
        assertThat(aggregationKey.equals(aggregationKey2)).isFalse();
        assertThat(aggregationKey2.equals(aggregationKey)).isFalse();
    }

    @Test
    public void testEqualsToDifferentType() throws Exception {
        AggregationKey aggregationKey = new AggregationKey(null, null, TransactionType.B);
        assertThat(aggregationKey.equals("abcd")).isFalse();
    }

}

