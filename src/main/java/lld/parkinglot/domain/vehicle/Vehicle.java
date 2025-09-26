package lld.parkinglot.domain.vehicle;

import lld.parkinglot.domain.parkingLot.ParkingSize;

public abstract class Vehicle {
    private final String plate;
    private final ParkingSize size;

    public Vehicle(String plate, ParkingSize size) {
        this.plate = plate;
        this.size = size;
    }

    public String getPlate() {
        return plate;
    }

    public ParkingSize getSize() {
        return size;
    }
}
