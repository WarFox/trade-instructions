package services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AggregationService {

    Map<LocalDate, BigDecimal> incomingByDate();

    Map<LocalDate, BigDecimal> outgoingByDate();

    List<String> incomingRank();

    List<String> outgoingRank();
}
