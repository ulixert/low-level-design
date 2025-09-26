package lld.parkinglot.strategy.allocation;

import lld.parkinglot.domain.parkingLot.ParkingSpot;
import lld.parkinglot.domain.vehicle.Vehicle;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class NearestFirstAllocationStrategy implements AllocationStrategy {

    @Override
    public Optional<ParkingSpot> allocateSpot(Map<String, ParkingSpot> availableSpots, Vehicle vehicle) {
        return availableSpots.values().stream()
                .filter(ParkingSpot::isAvailable)
                .filter(spot -> spot.canFitVehicle(vehicle))
                .min(Comparator.comparing(ParkingSpot::getLevel)
                        .thenComparing(ParkingSpot::getId));
    }
}