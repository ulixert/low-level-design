package lld.parkinglot.service;

import lld.parkinglot.domain.parkingLot.ParkingSpot;
import lld.parkinglot.domain.parkingLot.ParkingTicket;
import lld.parkinglot.domain.vehicle.Vehicle;
import lld.parkinglot.strategy.allocation.AllocationStrategy;
import lld.parkinglot.strategy.fee.FeeStrategy;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotService {
    private final Map<String, ParkingSpot> availableSpots = new HashMap<>();
    private final Map<String, ParkingTicket> activeTickets = new HashMap<>();

    private final AllocationStrategy allocationStrategy;
    private final FeeStrategy feeStrategy;

    public ParkingLotService(AllocationStrategy allocationStrategy, FeeStrategy feeStrategy) {
        this.allocationStrategy = allocationStrategy;
        this.feeStrategy = feeStrategy;
    }

    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        String plate = vehicle.getPlate();
        if (activeTickets.containsKey(plate)) {
            throw new IllegalStateException("Vehicle already parked: " + plate);
        }

        ParkingSpot spot = allocationStrategy
                .allocateSpot(availableSpots, vehicle)
                .orElseThrow(() -> new IllegalStateException("No available parking spot for vehicle: " + plate));

        spot.markOccupied();
        availableSpots.remove(spot.getId());

        ParkingTicket ticket = new ParkingTicket(vehicle, spot);
        activeTickets.put(plate, ticket);
        return ticket;
    }

    public synchronized BigDecimal exitVehicle(Vehicle vehicle) {
        String plate = vehicle.getPlate();
        ParkingTicket ticket = activeTickets.get(plate);
        if (ticket == null) {
            throw new IllegalArgumentException("Vehicle not found in parking lot: " + plate);
        }

        ticket.setExitTime();
        ParkingSpot spot = ticket.getSpot();
        spot.markFree();

        availableSpots.put(spot.getId(), spot);
        activeTickets.remove(plate);

        return feeStrategy.calculateFee(ticket);
    }

    public synchronized void addParkingSpot(ParkingSpot spot) {
        availableSpots.put(spot.getId(), spot);
    }
}