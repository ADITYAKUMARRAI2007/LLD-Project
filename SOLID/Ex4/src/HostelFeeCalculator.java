import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final RoomPricingPolicy roomPricingPolicy;
    private final AddOnPricingPolicy addOnPricingPolicy;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.roomPricingPolicy = new LegacyRoomPricingPolicy();
        this.addOnPricingPolicy = new DefaultAddOnPricingPolicy();
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        Money total = roomPricingPolicy.monthlyBaseFor(req.roomType);
        for (AddOn a : req.addOns) {
            total = total.plus(addOnPricingPolicy.monthlyChargeFor(a));
        }
        return total;
    }
}
