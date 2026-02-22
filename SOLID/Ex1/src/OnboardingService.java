import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final InputParser parser = new InputParser();
    private final ValidationService validator = new ValidationService();
    private final OutputRenderer renderer = new OutputRenderer();

    public OnboardingService(StudentRepository repo) {
        this.repo = repo;
    }

    public void registerFromRawInput(String raw) {

        System.out.println("INPUT: " + raw);

        // 1️⃣ Parse
        StudentRequestData data = parser.parse(raw);

        // 2️⃣ Validate
        List<String> errors = validator.validate(data);

        if (!errors.isEmpty()) {
            renderer.showErrors(errors);
            return;
        }

        // 3️⃣ Generate ID
        String id = IdUtil.nextStudentId(repo.count());

        // 4️⃣ Create domain record
        StudentRecord rec =new StudentRecord(id, data.name, data.email, data.phone, data.program);

        // 5️⃣ Save
        repo.save(rec);

        // 6️⃣ Print success
        renderer.showSuccess(rec, repo.count());
    }
}