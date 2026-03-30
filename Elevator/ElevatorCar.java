import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ElevatorCar implements Maintainable, Monitorable, Loggable {
    private final String id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private ElevatorStatus status;
    private final double weightLimit;
    private final Door door;
    private final Display display;
    private final WeightSensor weightSensor;
    private final Alarm alarm;
    private final VoiceAnnouncement voiceAnnouncement;
    private final MovementController movementController;
    private final DoorController doorController;
    private final InternalPanel internalPanel;
    private final List<ElevatorObserver> observers;
    private final PriorityQueue<Integer> destinations;
    private Language language;

    public ElevatorCar(String id,
                       int startFloor,
                       double weightLimit,
                       MovementController movementController,
                       DoorController doorController,
                       VoiceAnnouncement voiceAnnouncement,
                       int maxFloor) {
        this.id = id;
        this.currentFloor = startFloor;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.status = ElevatorStatus.ACTIVE;
        this.weightLimit = weightLimit;
        this.door = new Door();
        this.display = new Display();
        this.weightSensor = new WeightSensor(weightLimit);
        this.alarm = new Alarm();
        this.voiceAnnouncement = voiceAnnouncement;
        this.movementController = movementController;
        this.doorController = doorController;
        this.observers = new ArrayList<>();
        this.destinations = new PriorityQueue<>();
        this.language = Language.ENGLISH;
        this.alarm.bindElevator(this);
        this.internalPanel = new InternalPanel(new ElevatorController(this), maxFloor);
        updateDisplay("Initialized");
    }

    public synchronized void addInternalDestination(int floor) {
        if (!canServeRequests()) {
            notifyObservers("Ignored internal request for floor " + floor);
            return;
        }
        destinations.offer(floor);
        notifyObservers("Internal destination added: " + floor);
    }

    public synchronized void addExternalPickup(int floor) {
        if (!canServeRequests()) {
            notifyObservers("Ignored external request for floor " + floor);
            return;
        }
        destinations.offer(floor);
        notifyObservers("External pickup added: " + floor);
    }

    public synchronized void step() {
        if (status == ElevatorStatus.EMERGENCY_STOPPED || state == ElevatorState.UNDER_MAINTENANCE) {
            updateDisplay("Unavailable");
            return;
        }

        if (weightSensor.isOverloaded()) {
            movementController.stop(this);
            voiceAnnouncement.speak("Overload detected. Elevator will not move.", language);
            notifyObservers("Overloaded. Current=" + weightSensor.getCurrentWeight() + " limit=" + weightLimit);
            return;
        }

        if (destinations.isEmpty()) {
            direction = Direction.IDLE;
            state = ElevatorState.IDLE;
            updateDisplay("Idle");
            return;
        }

        int target = destinations.peek();

        if (currentFloor == target) {
            movementController.stop(this);
            destinations.poll();
            openDoor();
            voiceAnnouncement.speak("Reached floor " + currentFloor, language);
            state = ElevatorState.IDLE;
            direction = Direction.IDLE;
            updateDisplay("Reached destination");
            return;
        }

        if (currentFloor < target) {
            currentFloor++;
            direction = Direction.UP;
            state = ElevatorState.MOVING_UP;
            movementController.moveUp(this);
            updateDisplay("Moving up");
        } else {
            currentFloor--;
            direction = Direction.DOWN;
            state = ElevatorState.MOVING_DOWN;
            movementController.moveDown(this);
            updateDisplay("Moving down");
        }
    }

    public synchronized void openDoor() {
        doorController.open(this);
        notifyObservers("Door opened");
    }

    public synchronized void closeDoor() {
        if (weightSensor.isOverloaded()) {
            voiceAnnouncement.speak("Cannot close door. Overloaded.", language);
            notifyObservers("Door close blocked due to overload");
            return;
        }
        doorController.close(this);
        notifyObservers("Door closed");
    }

    public synchronized void emergencyStop(String reason) {
        status = ElevatorStatus.EMERGENCY_STOPPED;
        movementController.stop(this);
        direction = Direction.IDLE;
        updateDisplay("Emergency Stop");
        voiceAnnouncement.speak("Emergency stop activated. " + reason, language);
        notifyObservers("Emergency stop: " + reason);
    }

    public synchronized void resetFromEmergency() {
        if (state != ElevatorState.UNDER_MAINTENANCE) {
            status = ElevatorStatus.ACTIVE;
            updateDisplay("Recovered from emergency");
            notifyObservers("Recovered from emergency");
        }
    }

    public boolean canServeRequests() {
        return status == ElevatorStatus.ACTIVE && state != ElevatorState.UNDER_MAINTENANCE;
    }

    @Override
    public void enterMaintenance() {
        state = ElevatorState.UNDER_MAINTENANCE;
        status = ElevatorStatus.OUT_OF_SERVICE;
        updateDisplay("Maintenance Mode");
        notifyObservers("Entered maintenance");
    }

    @Override
    public void exitMaintenance() {
        state = ElevatorState.IDLE;
        status = ElevatorStatus.ACTIVE;
        updateDisplay("Maintenance cleared");
        notifyObservers("Exited maintenance");
    }

    @Override
    public boolean isUnderMaintenance() {
        return state == ElevatorState.UNDER_MAINTENANCE;
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String event) {
        for (ElevatorObserver observer : observers) {
            observer.onElevatorEvent(this, event);
        }
    }

    private void updateDisplay(String message) {
        display.update(currentFloor, direction, message);
        display.show();
    }

    public void updateCurrentWeight(double currentWeight) {
        weightSensor.updateWeight(currentWeight);
        notifyObservers("Weight updated: " + currentWeight);
    }

    @Override
    public String currentStatus() {
        return "ElevatorCar{id='" + id + "', floor=" + currentFloor +
                ", direction=" + direction + ", state=" + state +
                ", status=" + status + "}";
    }

    @Override
    public String logData() {
        return currentStatus() + ", door=" + door.getState();
    }

    public String getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public ElevatorStatus getStatus() {
        return status;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public Door getDoor() {
        return door;
    }

    public Display getDisplay() {
        return display;
    }

    public WeightSensor getWeightSensor() {
        return weightSensor;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public InternalPanel getInternalPanel() {
        return internalPanel;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}