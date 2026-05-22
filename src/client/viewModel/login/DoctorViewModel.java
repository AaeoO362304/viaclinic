package client.viewModel.login;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DoctorViewModel {
    private final StringProperty name;
    private final LoginViewModel loginViewModel;

    public DoctorViewModel(LoginViewModel loginViewModel) {
        this.name = new SimpleStringProperty("");
        this.loginViewModel = loginViewModel;
    }

    public void clear() { name.set(""); }
    public StringProperty getNameProperty() { return name; }
    public LoginViewModel getLoginViewModel() { return loginViewModel; }
}
