import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeatLockService {
    private final int holdDurationInMinutes;

    public SeatLockService(int holdDurationInMinutes) {
        this.holdDurationInMinutes = holdDurationInMinutes;
    }

    public boolean lockSeats(Show show, List<String> seatIds, String userId) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(holdDurationInMinutes);
        List<ShowSeat> lockedSeats = new ArrayList<>();

        for (ShowSeat showSeat : show.getShowSeats()) {
            if (seatIds.contains(showSeat.getSeat().getSeatId())) {
                boolean locked = showSeat.tryHold(userId, expiryTime);
                if (!locked) {
                    for (ShowSeat lockedSeat : lockedSeats) {
                        lockedSeat.releaseHold(userId);
                    }
                    return false;
                }
                lockedSeats.add(showSeat);
            }
        }
        return true;
    }

    public void confirmSeats(Show show, List<String> seatIds, String userId) {
        for (ShowSeat showSeat : show.getShowSeats()) {
            if (seatIds.contains(showSeat.getSeat().getSeatId())) {
                showSeat.confirmBooking(userId);
            }
        }
    }

    public void releaseSeats(Show show, List<String> seatIds, String userId) {
        for (ShowSeat showSeat : show.getShowSeats()) {
            if (seatIds.contains(showSeat.getSeat().getSeatId())) {
                showSeat.releaseHold(userId);
            }
        }
    }

    public void releaseExpiredHolds(Show show) {
        LocalDateTime now = LocalDateTime.now();
        for (ShowSeat showSeat : show.getShowSeats()) {
            showSeat.releaseHoldIfExpired(now);
        }
    }
}