package server.model.auth;

public class LoginProxy implements LoginService
{
    private final LoginService realService;

    public LoginProxy(LoginService realService)
    {
        this.realService = realService;
    }

    public LoginProxy()
    {
        this(new LoginAuthenticator());
    }

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

    @Override
    public void logout()
    {
        realService.logout();
    }

    @Override
    public Session getCurrentSession()
    {
        return realService.getCurrentSession();
    }

    @Override
    public boolean isLoggedIn()
    {
        return realService.isLoggedIn();
    }

    @Override
    public boolean hasRole(Role role)
    {
        return realService.hasRole(role);
    }

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
