/**
 * Factory class for creating different pen types.
 */
public class PenFactory {

    private PenFactory() {
    }

    public static AbstractPen createBallPen(String brand, String inkColor) {
        InkContainer ink = new Ink(inkColor, 100);
        Tip tip = new Tip(0.7);
        WritingStrategy strategy = new SmoothWritingStrategy();
        return new BallPen(brand, ink, tip, strategy);
    }

    public static AbstractPen createGelPen(String brand, String inkColor) {
        InkContainer ink = new Ink(inkColor, 120);
        Tip tip = new Tip(0.5);
        WritingStrategy strategy = new BoldWritingStrategy();
        return new GelPen(brand, ink, tip, strategy);
    }
}