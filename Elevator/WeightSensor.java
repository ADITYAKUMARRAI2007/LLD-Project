public class WeightSensor {
    private double currentWeight;
    private final double maxWeight;

    public WeightSensor(double maxWeight) {
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void updateWeight(double weight) {
        this.currentWeight = weight;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public boolean isOverloaded() {
        return currentWeight > maxWeight;
    }
}