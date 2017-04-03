package models;

import java.time.LocalDate;

public class AggregationKey {

    private final LocalDate date;
    private final String entity;
    private final TransactionType transactionType;

    public AggregationKey(LocalDate date, String entity, TransactionType transactionType) {
        this.date = date;
        this.entity = entity;
        this.transactionType = transactionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEntity() {
        return entity;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AggregationKey that = (AggregationKey) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (entity != null ? !entity.equals(that.entity) : that.entity != null) return false;
        return transactionType == that.transactionType;

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        return result;
    }

}
