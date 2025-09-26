package lld.parkinglot.domain.vehicle;

import lld.parkinglot.domain.parkingLot.ParkingSize;

public class Car extends Vehicle {
    public Car(String plate) {
        super(plate, ParkingSize.MEDIUM);
    }
}
