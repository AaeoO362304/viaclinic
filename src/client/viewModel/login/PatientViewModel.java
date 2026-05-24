package client.viewModel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * View model for the Patient screen.
 * Stores the data shown on screen and has the methods the controller calls.
 *
 * @author Kühn, Pástor, Kolodziejczyk, Bastola, Karki
 * @version 1.0
 */
public class PatientViewModel {
    /** The name. */
    private final StringProperty name;
    /** The login view model. */
    private final LoginViewModel loginViewModel;

    /**
     * Creates a new {@code PatientViewModel} initialised with the given login view model.
     *
     * @param loginViewModel the login view model
     */
    public PatientViewModel(LoginViewModel loginViewModel) {
        this.name = new SimpleStringProperty("");
        this.loginViewModel = loginViewModel;
    }

    /**
     * Clears the values in this view model.
     */
    public void clear() { name.set(""); }
    /**
     * Returns the name property used for data binding.
     *
     * @return the name property
     */
    public StringProperty getNameProperty() { return name; }
    /**
     * Returns the login view model.
     *
     * @return the login view model
     */
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
}
