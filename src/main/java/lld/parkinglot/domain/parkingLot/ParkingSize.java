package lld.parkinglot.domain.parkingLot;

public enum ParkingSize {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int capacityRank;

    ParkingSize(int capacityRank) {
        this.capacityRank = capacityRank;
    }

    public boolean canFit(ParkingSize vehicleSize) {
        if (this == SMALL) {
            return vehicleSize == SMALL;
        }

        return vehicleSize.capacityRank <= this.capacityRank;
    }
}
