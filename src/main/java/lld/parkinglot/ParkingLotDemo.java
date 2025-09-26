package lld.parkinglot;

import lld.parkinglot.domain.parkingLot.ParkingSize;
import lld.parkinglot.domain.parkingLot.ParkingSpot;
import lld.parkinglot.domain.vehicle.Car;
import lld.parkinglot.service.ParkingLotService;
import lld.parkinglot.strategy.allocation.AllocationStrategy;
import lld.parkinglot.strategy.allocation.NearestFirstAllocationStrategy;
import lld.parkinglot.strategy.fee.FeeStrategy;
import lld.parkinglot.strategy.fee.FlatWithCapsFeeStrategy;

import java.util.random.RandomGenerator;

public class ParkingLotDemo {

    public static void main(String[] args) throws InterruptedException {
        RandomGenerator randomGenerator = RandomGenerator.getDefault();

        FeeStrategy feeStrategy = new FlatWithCapsFeeStrategy(4, 12);
        AllocationStrategy allocationStrategy = new NearestFirstAllocationStrategy();

        ParkingLotService parkingLotService = new ParkingLotService(allocationStrategy, feeStrategy);
        for (int i = 1; i <= 10; i++) {
            ParkingSpot spot = new ParkingSpot(Integer.toString(i), i % 3, ParkingSize.values()[randomGenerator.nextInt(0, 3)]);
            parkingLotService.addParkingSpot(spot);
        }

        System.out.println(parkingLotService.parkVehicle(new Car("KA-01-HH-1234")));
        System.out.println(parkingLotService.parkVehicle(new Car("KA-01-HH-9999")));
        System.out.println(parkingLotService.parkVehicle(new Car("KA-01-BB-0001")));

        Thread.sleep(10000);

        System.out.println(parkingLotService.exitVehicle(new Car("KA-01-HH-1234")));
    }
}