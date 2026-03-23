/**
 * Writing strategy for smooth writing.
 */
public class SmoothWritingStrategy implements WritingStrategy {

    @Override
    public String write(String content, String inkColor) {
        return "[Smooth Writing in " + inkColor + "]: " + content;
    }

    @Override
    public int inkRequired(String content) {
        return content.length();
    }

    @Override
    public String getWritingStyle() {
        return "Smooth";
    }
}