// Deliberately unused right now (smell: leftover artifact).
import java.util.*;
public class ConsoleInput {
    public Scanner scanner;
    public ConsoleInput() {
        this.scanner = new Scanner(System.in);
    }
    public String readLine() {
        return scanner.nextLine();
    }
}
