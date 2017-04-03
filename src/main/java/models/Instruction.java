package models;

import utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

/**
 * Instruction is immutable
 */
public class Instruction {

    private String entity;
    private TransactionType transactionType;
    private BigDecimal agreedFx;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private long units;
    private BigDecimal pricePerUnit;

    public boolean isIncoming() {
        return this.transactionType.equals(TransactionType.S);
    }

    public boolean isOutgoing() {
        return this.transactionType.equals(TransactionType.B);
    }

    public BigDecimal amountInUsd() {
        return pricePerUnit.multiply(agreedFx).multiply(BigDecimal.valueOf(units));
    }

    public String getEntity() {
        return entity;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public long getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public LocalDate getAdjustedSettlementDate() {
        if (DateUtils.isWeekEnd(settlementDate, currency)) {
            return DateUtils.nextWorkingDay(settlementDate, currency);
        }
        return settlementDate;
    }

    private Instruction(Builder builder) {
        this.entity = builder.entity;
        this.transactionType = builder.transactionType;
        this.agreedFx = builder.agreedFx;
        this.currency = builder.currency;
        this.instructionDate = builder.instructionDate;
        this.settlementDate = builder.settlementDate;
        this.units = builder.units;
        this.pricePerUnit = builder.pricePerUnit;
    }

    public static class Builder {
        private String entity;
        private TransactionType transactionType;
        private BigDecimal agreedFx;
        private Currency currency;
        private LocalDate instructionDate;
        private LocalDate settlementDate;
        private long units;
        private BigDecimal pricePerUnit;

        public Builder entity(String entity) {
            this.entity = entity;
            return this;
        }

        public Builder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public Builder agreedFx(BigDecimal agreedFx) {
            this.agreedFx = agreedFx;
            return this;
        }

        public Builder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Instruction build() {
            return new Instruction(this);
        }

        public Builder instructionDate(LocalDate instructionDate) {
            this.instructionDate = instructionDate;
            return this;
        }

        public Builder settlementDate(LocalDate settlementDate) {
            this.settlementDate = settlementDate;
            return this;
        }

        public Builder units(long units) {
            this.units = units;
            return this;
        }

        public Builder pricePerUnit(BigDecimal pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

    }

}
