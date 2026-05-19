package client.viewModel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import server.model.auth.AuthenticationException;
import server.model.auth.LoginService;
import server.model.auth.LoginProxy;
import server.model.auth.Role;
import server.model.auth.Session;
import server.model.bookAppointment.UserHandler;

public class LoginViewModel {
    private final UserHandler handler;
    private final LoginService auth;
    private Session currentSession;

    private final StringProperty username = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");
    private final StringProperty error    = new SimpleStringProperty("");

    public LoginViewModel(UserHandler handler) {
        this.handler = handler;
        this.auth = new LoginProxy();
    }

    public Role login()
    {
        error.set("");
        try
        {
            currentSession = auth.login(username.get(), password.get());
            return currentSession.getRole();
        }
        catch (AuthenticationException e)
        {
            error.set(e.getMessage());
            return null;
        }
    }

    public void clear() {
        username.set("");
        password.set("");
        error.set("");
    }

    public Session getCurrentSession()
    {
        return currentSession;
    }

    public StringProperty getUsernameProperty() { return username; }
    public StringProperty getPasswordProperty() { return password; }
    public StringProperty getErrorProperty()    { return error; }

    public LoginService getAuthService() { return auth; }
}
