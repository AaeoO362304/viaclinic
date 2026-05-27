package server.model.auth;

import server.model.User;

/**
 * Holds a logged-in user together with their role.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class Session
{
    /** The user. */
    private final User user;
    /** The role. */
    private final Role role;

    /**
     * Creates a new {@code Session} initialised with the given user, role.
     *
     * @param user the user
     * @param role the role
     */
    public Session(User user, Role role)
    {
        this.user = user;
        this.role = role;
    }

    /**
     * Returns the user.
     *
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * Returns the role.
     *
     * @return the role
     */
    public Role getRole()
    {
        return role;
    }

    /**
     * Returns the display name.
     *
     * @return the display name
     */
    public String getDisplayName()
    {
        if (user == null) return "";
        return user.getFirstName() + " " + user.getLastName();
    }
}
