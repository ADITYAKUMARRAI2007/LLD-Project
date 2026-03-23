/**
 * Abstraction for ink-related operations.
 */
public interface InkContainer {
    boolean hasInk(int amount);
    void consume(int amount);
    void refill();
    int getInkLevel();
    int getCapacity();
    String getColor();
}