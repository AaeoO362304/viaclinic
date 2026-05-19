package server.model.auth;

public interface LoginService
{
    Session login(String username, String password) throws AuthenticationException;

    void logout();

    Session getCurrentSession();

    boolean isLoggedIn();

    boolean hasRole(Role role);
}
