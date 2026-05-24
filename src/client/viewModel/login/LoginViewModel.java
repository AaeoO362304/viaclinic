package client.viewModel.login;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.RoleDTO;
import shared.SessionDTO;

/**
 * View model for the Login screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class LoginViewModel {
    /** The client. */
    private final ClinicClient client;
    /** The current session. */
    private SessionDTO currentSession;

    /** The username. */
    private final StringProperty username = new SimpleStringProperty("");
    /** The password. */
    private final StringProperty password = new SimpleStringProperty("");
    /** The error. */
    private final StringProperty error = new SimpleStringProperty("");

    /**
     * Creates a new {@code LoginViewModel} initialised with the given client.
     *
     * @param client the client
     */
    public LoginViewModel(ClinicClient client) {
        this.client = client;
    }

    /**
     * Checks the login and starts a session.
     *
     * @return the resulting role DTO
     */
    public RoleDTO login() {
        error.set("");
        try {
            currentSession = client.login(username.get(), password.get());
            return currentSession == null ? null : currentSession.getRole();
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Login failed." : e.getMessage());
            return null;
        }
    }

    /**
     * Logs the user out.
     */
    public void logout() {
        try {
            client.logout();
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Logout failed." : e.getMessage());
        }
        currentSession = null;
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() {
        username.set("");
        password.set("");
        error.set("");
    }

    /**
     * Returns the current session.
     *
     * @return the current session
     */
    public SessionDTO getCurrentSession() { return currentSession; }
    /**
     * Returns the username property used for data binding.
     *
     * @return the username property
     */
    public StringProperty getUsernameProperty() { return username; }
    /**
     * Returns the password property used for data binding.
     *
     * @return the password property
     */
    public StringProperty getPasswordProperty() { return password; }
    /**
     * Returns the error property used for data binding.
     *
     * @return the error property
     */
    public StringProperty getErrorProperty() { return error; }
}
