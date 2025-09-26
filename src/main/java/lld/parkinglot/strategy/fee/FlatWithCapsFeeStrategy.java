package lld.parkinglot.strategy.fee;

import lld.parkinglot.domain.parkingLot.ParkingTicket;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class FlatWithCapsFeeStrategy implements FeeStrategy {
    private final BigDecimal hourlyRate;
    private final BigDecimal maxPerStay;

    public FlatWithCapsFeeStrategy(double hourlyRate, double maxPerStay) {
        this.hourlyRate = BigDecimal.valueOf(hourlyRate);
        this.maxPerStay = BigDecimal.valueOf(maxPerStay);
    }

    @Override
    public BigDecimal calculateFee(ParkingTicket ticket) {
        long hours = getParkingDurationHours(ticket);
        BigDecimal fee = hourlyRate.multiply(BigDecimal.valueOf(hours));
        return fee.min(maxPerStay).setScale(2, RoundingMode.HALF_UP);
    }
}