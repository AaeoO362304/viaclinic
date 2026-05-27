package server.model.auth;

/**
 * Interface for logging in and logging out.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public interface LoginService
{
    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session
     * @throws AuthenticationException if the operation cannot be completed
     */
    Session login(String username, String password) throws AuthenticationException;

    /**
     * Logs the user out.
     */
    void logout();

    /**
     * Returns the current session.
     *
     * @return the current session
     */
    Session getCurrentSession();

    /**
     * Indicates whether logged in.
     *
     * @return {@code true} if logged in, otherwise {@code false}
     */
    boolean isLoggedIn();

    /**
     * Indicates whether role.
     *
     * @param role the role
     * @return {@code true} if role, otherwise {@code false}
     */
    boolean hasRole(Role role);
}
