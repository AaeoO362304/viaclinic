package shared;

/**
 * Holds the logged-in user data that is sent between the server and the client.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class SessionDTO {
    /** The user id. */
    private int userId;
    /** The first name. */
    private String firstName;
    /** The last name. */
    private String lastName;
    /** The username. */
    private String username;
    /** The role. */
    private RoleDTO role;

    /**
     * Creates a new {@code SessionDTO} instance.
     */
    public SessionDTO() {}

    /**
     * Creates a new {@code SessionDTO} initialised with the given user id, first name, last name, username, role.
     *
     * @param userId the identifier of the user
     * @param firstName the first name
     * @param lastName the last name
     * @param username the username
     * @param role the role
     */
    public SessionDTO(int userId, String firstName, String lastName, String username, RoleDTO role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
    }

    /**
     * Returns the user id.
     *
     * @return the user id
     */
    public int getUserId() { return userId; }
    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }
    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }
    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() { return username; }
    /**
     * Returns the role.
     *
     * @return the role
     */
    public RoleDTO getRole() { return role; }

    /**
     * Sets the user id.
     *
     * @param userId the identifier of the user
     */
    public void setUserId(int userId) { this.userId = userId; }
    /**
     * Sets the first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }
    /**
     * Sets the last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }
    /**
     * Sets the username.
     *
     * @param username the username
     */
    public void setUsername(String username) { this.username = username; }
    /**
     * Sets the role.
     *
     * @param role the role
     */
    public void setRole(RoleDTO role) { this.role = role; }

    /**
     * Returns the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        String first = firstName == null ? "" : firstName;
        String last = lastName == null ? "" : lastName;
        return (first + " " + last).trim();
    }
}
