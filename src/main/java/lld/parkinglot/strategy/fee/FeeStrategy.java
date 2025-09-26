package lld.parkinglot.strategy.fee;

import lld.parkinglot.domain.parkingLot.ParkingTicket;

import java.math.BigDecimal;
import java.time.Duration;

public interface FeeStrategy {
    BigDecimal calculateFee(ParkingTicket ticket);

    default long getParkingDurationHours(ParkingTicket ticket) {
        Duration duration = Duration.between(ticket.getEntryTime(), ticket.getExitTime());
        return Math.max(1, duration.plusMinutes(59).toHours());
    }
}
