package server.model.auth;

/**
 * Wraps the login service and keeps track of the logged-in session.
 * NOT USED ANYMORE!
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class LoginProxy implements LoginService
{
    /** The real service. */
    private final LoginService realService;

    /**
     * Creates a new {@code LoginProxy} initialised with the given real service.
     *
     * @param realService the real service
     */
    public LoginProxy(LoginService realService)
    {
        this.realService = realService;
    }

    /**
     * Creates a new {@code LoginProxy} instance.
     */
    public LoginProxy()
    {
        this(new LoginAuthenticator());
    }

    /**
     * Checks the login and starts a session.
     *
     * @param username the username
     * @param password the password
     * @return the resulting session
     * @throws AuthenticationException if the operation cannot be completed
     */
    @Override
    public Session login(String username, String password) throws AuthenticationException
    {
        if (username == null || username.isBlank())
        {
            throw new AuthenticationException("Please enter your username.");
        }
        if (password == null || password.isBlank())
        {
            throw new AuthenticationException("Please enter your password.");
        }

        if (realService.isLoggedIn())
        {
            throw new AuthenticationException(
                    "Another user is already logged in. Please log out first.");
        }

        return realService.login(username.trim(), password);
    }

    /**
     * Logs the user out.
     */
    @Override
    public void logout()
    {
        realService.logout();
    }

    /**
     * Returns the current session.
     *
     * @return the current session
     */
    @Override
    public Session getCurrentSession()
    {
        return realService.getCurrentSession();
    }

    /**
     * Indicates whether logged in.
     *
     * @return {@code true} if logged in, otherwise {@code false}
     */
    @Override
    public boolean isLoggedIn()
    {
        return realService.isLoggedIn();
    }

    /**
     * Indicates whether role.
     *
     * @param role the role
     * @return {@code true} if role, otherwise {@code false}
     */
    @Override
    public boolean hasRole(Role role)
    {
        return realService.hasRole(role);
    }

    /**
     * Checks that the user has the needed role.
     *
     * @param allowed the allowed
     * @throws AuthenticationException if the operation cannot be completed
     */
    public void requireRole(Role... allowed) throws AuthenticationException
    {
        if (!isLoggedIn())
        {
            throw new AuthenticationException("You must be logged in.");
        }
        for (Role r : allowed)
        {
            if (hasRole(r)) return;
        }
        throw new AuthenticationException("You do not have permission to do that.");
    }
}
