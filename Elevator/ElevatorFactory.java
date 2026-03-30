public class ElevatorFactory {
    private ElevatorFactory() {
    }

    public static ElevatorCar createElevator(String id,
                                             int startFloor,
                                             double weightLimit,
                                             int maxFloor,
                                             AnnouncementService announcementService) {
        HardwareControllerAdapter hardware = new HardwareControllerAdapter();
        MovementController movementController = new MovementController(hardware);
        DoorController doorController = new DoorController(hardware);
        VoiceAnnouncement voiceAnnouncement = new VoiceAnnouncement(announcementService);

        return new ElevatorCar(
                id,
                startFloor,
                weightLimit,
                movementController,
                doorController,
                voiceAnnouncement,
                maxFloor
        );
    }
}