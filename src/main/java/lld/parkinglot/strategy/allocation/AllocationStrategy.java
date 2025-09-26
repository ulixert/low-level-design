package lld.parkinglot.strategy.allocation;

import lld.parkinglot.domain.parkingLot.ParkingSpot;
import lld.parkinglot.domain.vehicle.Vehicle;

import java.util.Map;
import java.util.Optional;

public interface AllocationStrategy {
    Optional<ParkingSpot> allocateSpot(Map<String, ParkingSpot> availableSpots, Vehicle vehicle);
}