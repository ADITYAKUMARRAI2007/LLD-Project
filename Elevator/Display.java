public class Display {
    private int currentFloor;
    private Direction direction;
    private String message;

    public void update(int currentFloor, Direction direction, String message) {
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.message = message;
    }

    public void show() {
        System.out.println("[DISPLAY] Floor=" + currentFloor + ", Direction=" + direction + ", Message=" + message);
    }
}