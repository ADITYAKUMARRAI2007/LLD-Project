public class User {
    private final String userId;
    private final String name;
    private final String email;
    private final String phone;
    private final UserRole role;

    public User(String userId, String name, String email, String phone, UserRole role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public UserRole getRole() {
        return role;
    }
}