import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        EntryGate gateA = new EntryGate("GATE_A");
        EntryGate gateB = new EntryGate("GATE_B");

        ExitGate exitA = new ExitGate("EXIT_A");

        Map<String, EntryGate> entryGates = new HashMap<>();
        entryGates.put(gateA.getGateId(), gateA);
        entryGates.put(gateB.getGateId(), gateB);

        Map<String, ExitGate> exitGates = new HashMap<>();
        exitGates.put(exitA.getGateId(), exitA);
        ParkingSlot s1 = new ParkingSlot("L0-S1", SlotType.SMALL, 0);
        ParkingSlot s2 = new ParkingSlot("L0-M1", SlotType.MEDIUM, 0);
        ParkingSlot s3 = new ParkingSlot("L0-L1", SlotType.LARGE, 0);
        ParkingSlot s4 = new ParkingSlot("L1-S1", SlotType.SMALL, 1);
        ParkingSlot s5 = new ParkingSlot("L1-M1", SlotType.MEDIUM, 1);
        ParkingSlot s6 = new ParkingSlot("L1-L1", SlotType.LARGE, 1);

        Map<String, ParkingSlot> level0Slots = new HashMap<>();
        level0Slots.put(s1.getSlotId(), s1);
        level0Slots.put(s2.getSlotId(), s2);
        level0Slots.put(s3.getSlotId(), s3);

        Map<String, ParkingSlot> level1Slots = new HashMap<>();
        level1Slots.put(s4.getSlotId(), s4);
        level1Slots.put(s5.getSlotId(), s5);
        level1Slots.put(s6.getSlotId(), s6);

        ParkingLevel level0 = new ParkingLevel(0, level0Slots);
        ParkingLevel level1 = new ParkingLevel(1, level1Slots);

        List<ParkingLevel> levels = new ArrayList<>();
        levels.add(level0);
        levels.add(level1);
        Map<String, Integer> gateADistanceMap = new HashMap<>();
        gateADistanceMap.put("L0-S1", 2);
        gateADistanceMap.put("L0-M1", 4);
        gateADistanceMap.put("L0-L1", 6);
        gateADistanceMap.put("L1-S1", 8);
        gateADistanceMap.put("L1-M1", 10);
        gateADistanceMap.put("L1-L1", 12);

        Map<String, Integer> gateBDistanceMap = new HashMap<>();
        gateBDistanceMap.put("L0-S1", 5);
        gateBDistanceMap.put("L0-M1", 3);
        gateBDistanceMap.put("L0-L1", 7);
        gateBDistanceMap.put("L1-S1", 9);
        gateBDistanceMap.put("L1-M1", 11);
        gateBDistanceMap.put("L1-L1", 13);

        Comparator<ParkingSlot> gateAComparator = SlotDistanceComparatorFactory.create(gateADistanceMap);
        Comparator<ParkingSlot> gateBComparator = SlotDistanceComparatorFactory.create(gateBDistanceMap);
        Map<String, Map<SlotType, NavigableSet<ParkingSlot>>> availableSlotsByGate = new HashMap<>();

        availableSlotsByGate.put(gateA.getGateId(), createEmptySlotTypeMap(gateAComparator));
        availableSlotsByGate.put(gateB.getGateId(), createEmptySlotTypeMap(gateBComparator));

        List<ParkingSlot> allSlots = List.of(s1, s2, s3, s4, s5, s6);
        for (ParkingSlot slot : allSlots) {
            availableSlotsByGate.get(gateA.getGateId()).get(slot.getSlotType()).add(slot);
            availableSlotsByGate.get(gateB.getGateId()).get(slot.getSlotType()).add(slot);
        }
        Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();
        ParkingLot parkingLot = ParkingLot.init(
                levels,
                entryGates,
                exitGates,
                activeTickets,
                availableSlotsByGate
        );
        parkingLot.setSlotAssignmentStrategy(new NearestSlotAssignmentStrategy());

        Map<SlotType, Double> rates = new HashMap<>();
        rates.put(SlotType.SMALL, 20.0);
        rates.put(SlotType.MEDIUM, 40.0);
        rates.put(SlotType.LARGE, 60.0);

        RateCard rateCard = new RateCard(rates);
        parkingLot.setPricingStrategy(new HourlyPricingStrategy(rateCard));
        ParkingManager parkingManager = new ParkingManager(parkingLot);
        Vehicle vehicle = VehicleFactory.createVehicle("KA-01-AB-1234", VehicleType.CAR);

        LocalDateTime entryTime = LocalDateTime.now();
        Ticket ticket = parkingManager.park(vehicle, entryTime, SlotType.MEDIUM, gateA.getGateId());

        System.out.println("Vehicle Parked Successfully");
        System.out.println(ticket);
        LocalDateTime exitTime = entryTime.plusHours(3).plusMinutes(10);
        ParkingReceipt receipt = parkingManager.exit(ticket.getTicketId(), exitTime, exitA.getGateId());

        System.out.println("Vehicle Exited Successfully");
        System.out.println(receipt);
    }

    private static Map<SlotType, NavigableSet<ParkingSlot>> createEmptySlotTypeMap(Comparator<ParkingSlot> comparator) {
        Map<SlotType, NavigableSet<ParkingSlot>> map = new HashMap<>();
        map.put(SlotType.SMALL, new TreeSet<>(comparator));
        map.put(SlotType.MEDIUM, new TreeSet<>(comparator));
        map.put(SlotType.LARGE, new TreeSet<>(comparator));
        return map;
    }
}