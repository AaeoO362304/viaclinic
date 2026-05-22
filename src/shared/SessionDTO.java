package shared;

public class SessionDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private RoleDTO role;

    public SessionDTO() {}

    public SessionDTO(int userId, String firstName, String lastName, String username, RoleDTO role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return username; }
    public RoleDTO getRole() { return role; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setUsername(String username) { this.username = username; }
    public void setRole(RoleDTO role) { this.role = role; }

    public String getDisplayName() {
        String first = firstName == null ? "" : firstName;
        String last = lastName == null ? "" : lastName;
        return (first + " " + last).trim();
    }
}
