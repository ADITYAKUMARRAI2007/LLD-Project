public abstract class Button {
    private final String label;

    public Button(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public abstract void press();
}