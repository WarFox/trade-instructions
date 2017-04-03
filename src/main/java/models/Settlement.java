package models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Settlement {

    private final LocalDate date;
    private final String entity;
    private final TransactionType transactionType;
    private final BigDecimal amount;

    public Settlement(LocalDate date, String entity, TransactionType transactionType, BigDecimal amount) {
        this.date = date;
        this.entity = entity;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public boolean isIncoming() {
        return this.transactionType.equals(TransactionType.S);
    }

    public boolean isOutgoing() {
        return this.transactionType.equals(TransactionType.B);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEntity() {
        return entity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

}
