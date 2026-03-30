import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class ShowSeat {
    private final String showSeatId;
    private final Seat seat;
    private final Show show;
    private ShowSeatState state;
    private String lockedByUserId;
    private LocalDateTime lockExpiryTime;
    private final ReentrantLock lock;

    public ShowSeat(String showSeatId, Seat seat, Show show) {
        this.showSeatId = showSeatId;
        this.seat = seat;
        this.show = show;
        this.state = ShowSeatState.AVAILABLE;
        this.lock = new ReentrantLock();
    }

    public boolean tryHold(String userId, LocalDateTime expiryTime) {
        lock.lock();
        try {
            if (state != ShowSeatState.AVAILABLE) {
                return false;
            }
            state = ShowSeatState.HELD;
            lockedByUserId = userId;
            lockExpiryTime = expiryTime;
            return true;
        } finally {
            lock.unlock();
        }
    }

    public boolean confirmBooking(String userId) {
        lock.lock();
        try {
            if (state == ShowSeatState.HELD && userId.equals(lockedByUserId)) {
                state = ShowSeatState.BOOKED;
                lockedByUserId = null;
                lockExpiryTime = null;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void releaseHoldIfExpired(LocalDateTime now) {
        lock.lock();
        try {
            if (state == ShowSeatState.HELD && lockExpiryTime != null && now.isAfter(lockExpiryTime)) {
                state = ShowSeatState.AVAILABLE;
                lockedByUserId = null;
                lockExpiryTime = null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void releaseHold(String userId) {
        lock.lock();
        try {
            if (state == ShowSeatState.HELD && userId.equals(lockedByUserId)) {
                state = ShowSeatState.AVAILABLE;
                lockedByUserId = null;
                lockExpiryTime = null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void cancelBookedSeat() {
        lock.lock();
        try {
            if (state == ShowSeatState.BOOKED) {
                state = ShowSeatState.AVAILABLE;
            }
        } finally {
            lock.unlock();
        }
    }

    public String getShowSeatId() {
        return showSeatId;
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public ShowSeatState getState() {
        return state;
    }

    public String getLockedByUserId() {
        return lockedByUserId;
    }

    public LocalDateTime getLockExpiryTime() {
        return lockExpiryTime;
    }
}