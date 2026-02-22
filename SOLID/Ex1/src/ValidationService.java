import java.util.*;

public class ValidationService {

    public List<String> validate(StudentRequestData d) {

        List<String> errors = new ArrayList<>();

        if (d.name.isBlank()) errors.add("name is required");

        if (d.email.isBlank() || !d.email.contains("@"))
            errors.add("email is invalid");

        if (d.phone.isBlank() || !d.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");

        if (!(d.program.equals("CSE") || d.program.equals("AI") || d.program.equals("SWE")))
            errors.add("program is invalid");

        return errors;
    }
}