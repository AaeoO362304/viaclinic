package client.viewModel.login;

import client.model.ClinicClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.RoleDTO;
import shared.SessionDTO;

public class LoginViewModel {
    private final ClinicClient client;
    private SessionDTO currentSession;

    private final StringProperty username = new SimpleStringProperty("");
    private final StringProperty password = new SimpleStringProperty("");
    private final StringProperty error = new SimpleStringProperty("");

    public LoginViewModel(ClinicClient client) {
        this.client = client;
    }

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

    public void logout() {
        try {
            client.logout();
        }
        catch (Exception e) {
            error.set(e.getMessage() == null ? "Logout failed." : e.getMessage());
        }
        currentSession = null;
    }

    public void clear() {
        username.set("");
        password.set("");
        error.set("");
    }

    public SessionDTO getCurrentSession() { return currentSession; }
    public StringProperty getUsernameProperty() { return username; }
    public StringProperty getPasswordProperty() { return password; }
    public StringProperty getErrorProperty() { return error; }
}
