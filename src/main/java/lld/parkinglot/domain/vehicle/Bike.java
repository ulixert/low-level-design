package lld.parkinglot.domain.vehicle;

import lld.parkinglot.domain.parkingLot.ParkingSize;

public class Bike extends Vehicle {
    public Bike(String plate) {
        super(plate, ParkingSize.SMALL);
    }
}
