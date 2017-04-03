package services;

import models.AggregationKey;
import models.Instruction;
import models.Settlement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

/**
 * Aggregation Service takes a list of instructions
 * and produce various aggregations
 */
public class SimpleAggregationService implements AggregationService {

    private final List<Instruction> instructions;
    private List<Settlement> settlements = new ArrayList<>();

    public SimpleAggregationService(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    @Override
    public Map<LocalDate, BigDecimal> incomingByDate() {
        return instructions
                .stream()
                .filter(Instruction::isIncoming)
                .collect(groupingBy(Instruction::getAdjustedSettlementDate, reducing(BigDecimal.ZERO, Instruction::amountInUsd, BigDecimal::add)));
    }

    @Override
    public Map<LocalDate, BigDecimal> outgoingByDate() {
        return instructions
                .stream()
                .filter(Instruction::isOutgoing)
                .collect(groupingBy(Instruction::getAdjustedSettlementDate, reducing(BigDecimal.ZERO, Instruction::amountInUsd, BigDecimal::add)));
    }

    private static Function<Instruction, AggregationKey> aggregationKeyFunction = event ->
            new AggregationKey(event.getAdjustedSettlementDate(), event.getEntity(), event.getTransactionType());


    /**
     * Settlements is lazy initialized for optimization
     *
     * @return List of settlements
     */
    public List<Settlement> getSettlements() {
        if (this.settlements.isEmpty()) {
            this.settlements = settlements();
        }
        return settlements;
    }

    private List<Settlement> settlements() {
        return instructions.stream()
                .collect(groupingBy(aggregationKeyFunction, reducing(BigDecimal.ZERO, Instruction::amountInUsd, BigDecimal::add)))
                .entrySet()
                .stream()
                .map(entry -> {
                    AggregationKey key = entry.getKey();
                    return new Settlement(key.getDate(), key.getEntity(), key.getTransactionType(), entry.getValue());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> incomingRank() {
        return getSettlements().stream()
                .filter(Settlement::isIncoming)
                .collect(groupingBy(Settlement::getEntity, reducing(BigDecimal.ZERO, Settlement::getAmount, BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> outgoingRank() {
        return getSettlements().stream()
                .filter(Settlement::isOutgoing)
                .collect(groupingBy(Settlement::getEntity, reducing(BigDecimal.ZERO, Settlement::getAmount, BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
