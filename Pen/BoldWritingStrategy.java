/**
 * Writing strategy for bold writing.
 */
public class BoldWritingStrategy implements WritingStrategy {

    @Override
    public String write(String content, String inkColor) {
        return "[Bold Writing in " + inkColor + "]: " + content.toUpperCase();
    }

    @Override
    public int inkRequired(String content) {
        return content.length() * 2;
    }

    @Override
    public String getWritingStyle() {
        return "Bold";
    }
}