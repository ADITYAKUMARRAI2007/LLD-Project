import java.util.List;

public class OutputRenderer {

    public void showErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) System.out.println("- " + e);
    }

    public void showSuccess(StudentRecord rec, int total) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + total);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}