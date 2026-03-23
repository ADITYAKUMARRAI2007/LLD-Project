/**
 * Represents the tip/nib of the pen.
 */
public class Tip {
    private final double size;
    private boolean usable;

    public Tip(double size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Tip size must be greater than 0.");
        }
        this.size = size;
        this.usable = true;
    }

    public double getSize() {
        return size;
    }

    public boolean isUsable() {
        return usable;
    }

    public void damageTip() {
        usable = false;
    }

    public void repairTip() {
        usable = true;
    }

    @Override
    public String toString() {
        return "Tip{size=" + size + ", usable=" + usable + "}";
    }
}