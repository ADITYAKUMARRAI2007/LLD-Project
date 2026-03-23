/**
 * Abstract base class for all pen types.
 */
public abstract class AbstractPen implements Openable, Writable, Closable, Refillable {
    private final String brand;
    private boolean isOpen;
    private InkContainer inkContainer;
    private Tip tip;
    private WritingStrategy writingStrategy;

    public AbstractPen(String brand, InkContainer inkContainer, Tip tip, WritingStrategy writingStrategy) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Brand cannot be null or blank.");
        }
        if (inkContainer == null) {
            throw new IllegalArgumentException("InkContainer cannot be null.");
        }
        if (tip == null) {
            throw new IllegalArgumentException("Tip cannot be null.");
        }
        if (writingStrategy == null) {
            throw new IllegalArgumentException("WritingStrategy cannot be null.");
        }

        this.brand = brand;
        this.inkContainer = inkContainer;
        this.tip = tip;
        this.writingStrategy = writingStrategy;
        this.isOpen = false;
    }

    @Override
    public void start() {
        if (isOpen) {
            System.out.println("ℹ️ " + brand + " pen is already open.");
            return;
        }
        isOpen = true;
        System.out.println("🔓 " + brand + " pen started and ready to write.");
    }

    @Override
    public void close() {
        if (!isOpen) {
            System.out.println("ℹ️ " + brand + " pen is already closed.");
            return;
        }
        isOpen = false;
        System.out.println("🔒 " + brand + " pen closed.");
    }

    @Override
    public void write(String content) {
        validateWriteOperation(content);

        int requiredInk = writingStrategy.inkRequired(content);
        inkContainer.consume(requiredInk);

        String output = writingStrategy.write(content, inkContainer.getColor());
        System.out.println(output);
        System.out.println("Remaining ink: " + inkContainer.getInkLevel());
    }

    @Override
    public void refill() {
        inkContainer.refill();
    }

    private void validateWriteOperation(String content) {
        if (!isOpen) {
            throw new IllegalStateException("Cannot write because pen is closed.");
        }

        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank.");
        }

        if (!tip.isUsable()) {
            throw new IllegalStateException("Cannot write because tip is damaged.");
        }

        int requiredInk = writingStrategy.inkRequired(content);
        if (!inkContainer.hasInk(requiredInk)) {
            throw new IllegalStateException("Cannot write because ink is insufficient.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public InkContainer getInkContainer() {
        return inkContainer;
    }

    public void setInkContainer(InkContainer inkContainer) {
        if (inkContainer == null) {
            throw new IllegalArgumentException("InkContainer cannot be null.");
        }
        this.inkContainer = inkContainer;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        if (tip == null) {
            throw new IllegalArgumentException("Tip cannot be null.");
        }
        this.tip = tip;
    }

    public WritingStrategy getWritingStrategy() {
        return writingStrategy;
    }

    public void setWritingStrategy(WritingStrategy writingStrategy) {
        if (writingStrategy == null) {
            throw new IllegalArgumentException("WritingStrategy cannot be null.");
        }
        this.writingStrategy = writingStrategy;
    }

    @Override
    public String toString() {
        return "AbstractPen{" +
                "brand='" + brand + '\'' +
                ", isOpen=" + isOpen +
                ", tip=" + tip +
                ", writingStyle=" + writingStrategy.getWritingStyle() +
                ", ink=" + inkContainer +
                '}';
    }
}