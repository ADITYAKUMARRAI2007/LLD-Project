/**
 * Strategy interface for writing behavior.
 */
public interface WritingStrategy {
    String write(String content, String inkColor);
    int inkRequired(String content);
    String getWritingStyle();
}