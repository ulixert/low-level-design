package lld.parkinglot.domain.vehicle;

import lld.parkinglot.domain.parkingLot.ParkingSize;

public class Truck extends Vehicle {
    public Truck(String plate) {
        super(plate, ParkingSize.LARGE);
    }
}
