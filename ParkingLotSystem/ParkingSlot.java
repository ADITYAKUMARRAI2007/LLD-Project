import java.util.concurrent.locks.ReentrantLock;

public class ParkingSlot {
    private final String slotId;
    private final SlotType slotType;
    private final int levelNumber;
    private volatile SlotStatus status;
    private Vehicle parkedVehicle;
    private final ReentrantLock lock;

    public ParkingSlot(String slotId, SlotType slotType, int levelNumber) {
        if (slotId == null || slotId.isBlank()) {
            throw new IllegalArgumentException("Slot id cannot be null or blank.");
        }
        if (slotType == null) {
            throw new IllegalArgumentException("Slot type cannot be null.");
        }

        this.slotId = slotId;
        this.slotType = slotType;
        this.levelNumber = levelNumber;
        this.status = SlotStatus.AVAILABLE;
        this.lock = new ReentrantLock();
    }

    public boolean tryReserve(Vehicle vehicle) {
        lock.lock();
        try {
            if (status == SlotStatus.OCCUPIED) {
                return false;
            }
            this.parkedVehicle = vehicle;
            this.status = SlotStatus.OCCUPIED;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            this.parkedVehicle = null;
            this.status = SlotStatus.AVAILABLE;
        } finally {
            lock.unlock();
        }
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId='" + slotId + '\'' +
                ", slotType=" + slotType +
                ", levelNumber=" + levelNumber +
                ", status=" + status +
                '}';
    }
}