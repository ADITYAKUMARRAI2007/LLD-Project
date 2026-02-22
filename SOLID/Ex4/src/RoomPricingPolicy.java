public interface RoomPricingPolicy {
    Money monthlyBaseFor(int roomType);
}
