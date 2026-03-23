/**
 * Represents the ink inside a pen.
 */
public class Ink implements InkContainer {
    private final String color;
    private final int capacity;
    private int currentLevel;

    public Ink(String color, int capacity) {
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Ink color cannot be null or blank.");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Ink capacity must be greater than 0.");
        }

        this.color = color;
        this.capacity = capacity;
        this.currentLevel = capacity;
    }

    @Override
    public boolean hasInk(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Ink amount cannot be negative.");
        }
        return currentLevel >= amount;
    }

    @Override
    public void consume(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Ink consumption cannot be negative.");
        }
        if (!hasInk(amount)) {
            throw new IllegalStateException("Not enough ink available.");
        }
        currentLevel -= amount;
    }

    @Override
    public void refill() {
        currentLevel = capacity;
        System.out.println("✅ Ink refilled to full capacity.");
    }

    @Override
    public int getInkLevel() {
        return currentLevel;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Ink{color='" + color + "', currentLevel=" + currentLevel + "/" + capacity + "}";
    }
}